package br.com.fiap.mottu.challenge.demo.services;

import br.com.fiap.mottu.challenge.demo.domain.model.User;
import br.com.fiap.mottu.challenge.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service responsible for loading user-specific data during the authentication process.
 * This class implements Spring Security's UserDetailsService interface.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo; // Corrected variable name

    // Corrected constructor name and argument binding (fixed capitalization)
    public UserService( UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

         return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}