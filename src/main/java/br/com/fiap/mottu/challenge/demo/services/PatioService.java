package br.com.fiap.mottu.challenge.demo.services;

import br.com.fiap.mottu.challenge.demo.domain.model.Patio;
import br.com.fiap.mottu.challenge.demo.repository.PatioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatioService {

    @Autowired
    private PatioRepo patioRepo;

  @Transactional(readOnly = true)
  public Page<Patio> buscarPatios(String nome, Integer capacidadeMaxima, Integer capacidadeMinima, Pageable pageable) {
      if (nome != null && capacidadeMaxima != null) {
          return (Page<Patio>) patioRepo.findByNomeContainingIgnoreCase(nome, pageable)
                  .map(patio -> patio.getCapacidadeMaxima() >= capacidadeMaxima ? patio : null)
                  .filter(patio -> patio != null);
      } else if (nome != null) {
          return patioRepo.findByNomeContainingIgnoreCase(nome, pageable);
      } else if (capacidadeMinima != null) {
          return patioRepo.findByCapacidadeMaximaGreaterThanEqual(capacidadeMinima, pageable);
      } else {
          return patioRepo.findAll(pageable);
      }
  }
    @Transactional(readOnly = true)
    public Optional<Patio> buscarPorId(Long id) {
        return patioRepo.findById(id);
    }

    @Transactional
    public Patio salvar(Patio patio) {
        return patioRepo.save(patio);
    }

    @Transactional
    public Patio atualizar(Long id, Patio patioAtualizado) {
        Patio patio = patioRepo.findById(id).orElseThrow(() -> new RuntimeException("Pátio não encontrado"));
        patio.setNome(patioAtualizado.getNome());
        patio.setCapacidadeMaxima(patioAtualizado.getCapacidadeMaxima());
        patio.setAreaTotal(patioAtualizado.getAreaTotal());
        patio.setObservacoes(patioAtualizado.getObservacoes());
        return patioRepo.save(patio);
    }

    @Transactional
    public void deletar(Long id) {
        Patio patio = patioRepo.findById(id).orElseThrow(() -> new RuntimeException("Pátio não encontrado"));
        patioRepo.delete(patio);
    }
}