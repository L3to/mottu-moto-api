package br.com.fiap.mottu.challenge.demo.repository;

import br.com.fiap.mottu.challenge.demo.domain.model.Moto;
import br.com.fiap.mottu.challenge.demo.utils.StatusSensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MotoRepo extends JpaRepository<Moto, Long> {


        Optional<Moto> findByPlaca(String placa);

        Optional<Moto> findByChassi(String chassi);

        List<Moto> findByAtivaTrue();

        List<Moto> findByStatusSensor(StatusSensor statusSensor);

        List<Moto> findByModeloContainingIgnoreCase(String modelo);

        List<Moto> findByPatioId(Long patioId);

        Page<Moto> findByModeloContainingIgnoreCase(String modelo, Pageable pageable);

        Page<Moto> findByStatusSensor(StatusSensor statusSensor, Pageable pageable);

        Page<Moto> findByModeloContainingIgnoreCaseAndStatusSensor(String modelo, StatusSensor statusSensor, Pageable pageable);

    }


