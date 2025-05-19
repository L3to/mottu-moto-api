package br.com.fiap.mottu.challenge.demo.services;

import br.com.fiap.mottu.challenge.demo.domain.dto.MotoFullDTO;
import br.com.fiap.mottu.challenge.demo.domain.model.Localizacao;
import br.com.fiap.mottu.challenge.demo.domain.model.Moto;
import br.com.fiap.mottu.challenge.demo.domain.model.Patio;
import br.com.fiap.mottu.challenge.demo.domain.model.StatusSensor;
import br.com.fiap.mottu.challenge.demo.exceptions.GlobalException;
import br.com.fiap.mottu.challenge.demo.repository.MotoRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MotoService {
    @Autowired
    private MotoRepo motoRepo;
    @Autowired
    private PatioService patioService;

    @Cacheable("motos")
    @Transactional(readOnly = true)
    public Page<MotoFullDTO> buscarMotos(String modelo, StatusSensor statusSensor, Pageable pageable, String sort) {
        Sort sortOrder = Sort.unsorted();
        if (sort != null) {
            String[] sortParams = sort.split(",");
            String sortField = sortParams[0];
            String sortDirection = sortParams.length > 1 ? sortParams[1] : "asc";
            sortOrder = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        }

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortOrder);

        Page<Moto> motos;
        if (modelo != null && statusSensor != null) {
            motos = motoRepo.findByModeloContainingIgnoreCaseAndStatusSensor(modelo, statusSensor, sortedPageable);
        } else if (modelo != null) {
            motos = motoRepo.findByModeloContainingIgnoreCase(modelo, sortedPageable);
        } else if (statusSensor != null) {
            motos = motoRepo.findByStatusSensor(statusSensor, sortedPageable);
        } else {
            // Certifique-se de que este trecho está funcionando corretamente
            motos = motoRepo.findAll(sortedPageable);
        }

        return motos.map(MotoFullDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<MotoFullDTO> buscarPorId(Long id) {
        return motoRepo.findById(id).map(MotoFullDTO::new);
    }

    @Transactional
    public MotoFullDTO salvar(MotoFullDTO motoFullDTO) {
        validarPlacaUnica(motoFullDTO.getPlaca());

        Moto moto = motoFullDTO.toEntity();

        if (moto.getPatio() != null && moto.getPatio().getId() != null) {
            Patio patio = patioService.buscarPorId(moto.getPatio().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));
            moto.setPatio(patio);
        }

        Moto motoSalva = motoRepo.save(moto);
        return new MotoFullDTO(motoSalva);
    }

    public void validarPlacaUnica(String placa) {
        if (motoRepo.findByPlaca(placa).isPresent()) {
            throw new IllegalArgumentException("Já existe uma moto cadastrada com esta placa.");
        }
    }

    @Transactional
    public MotoFullDTO atualizar(Long id, MotoFullDTO motoFullDTO) {
        Moto moto = motoRepo.findById(id).orElseThrow(() -> new RuntimeException("Moto não encontrada"));
        moto.setModelo(motoFullDTO.getModelo());
        moto.setPlaca(motoFullDTO.getPlaca());
        moto.setChassi(motoFullDTO.getChassi());
        moto.setAtiva(motoFullDTO.isAtiva());
        moto.setStatusSensor(motoFullDTO.getStatusSensor());
        moto.setLocalizacaoAtual(motoFullDTO.toEntity().getLocalizacaoAtual());
        Moto motoAtualizada = motoRepo.save(moto);
        return new MotoFullDTO(motoAtualizada);
    }

    @Transactional
    public void deletar(Long id) {
        Moto moto = motoRepo.findById(id).orElseThrow(() -> new RuntimeException("Moto não encontrada"));
        motoRepo.delete(moto);
    }

    @Transactional
    public MotoFullDTO adicionarPatioELocalizacao(Long motoId, Long patioId, Localizacao localizacao) {
        Moto moto = buscarMotoPorId(motoId);

    Patio patio = patioService.buscarPorId(patioId)
            .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));

        validarCapacidadePatio(patio);

        moto.setPatio(patio);
        moto.setLocalizacaoAtual(localizacao);

        Moto motoAtualizada = motoRepo.save(moto);

        return new MotoFullDTO(motoAtualizada);
    }

    private Moto buscarMotoPorId(Long motoId) {
        return motoRepo.findById(motoId)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
    }

    private void validarCapacidadePatio(Patio patio) {
        if (patio.getMotos().size() >= patio.getCapacidadeMaxima()) {
            throw new IllegalStateException("Pátio está com capacidade máxima");
        }
    }

}