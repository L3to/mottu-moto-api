package br.com.fiap.mottu.challenge.demo.repository;

import br.com.fiap.mottu.challenge.demo.domain.model.Patio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatioRepo extends JpaRepository<Patio, Long> {

    Optional<Patio> findByNome(String nome);

    List<Patio> findByNomeContainingIgnoreCase(String nome);

    List<Patio> findByCapacidadeMaximaGreaterThanEqual(int capacidadeMinima);
    Page<Patio> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Patio> findByCapacidadeMaximaGreaterThanEqual(int capacidadeMinima, Pageable pageable);


}
