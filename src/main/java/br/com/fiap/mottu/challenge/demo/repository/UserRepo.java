package br.com.fiap.mottu.challenge.demo.repository;

import br.com.fiap.mottu.challenge.demo.domain.model.User;
import br.com.fiap.mottu.challenge.demo.utils.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    long countByRole(UserRole role);

}