package br.com.fiap.mottu.challenge.demo.controllers;

import br.com.fiap.mottu.challenge.demo.domain.model.User;
import br.com.fiap.mottu.challenge.demo.repository.UserRepo;
import br.com.fiap.mottu.challenge.demo.utils.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {

        private final UserRepo userRepo;
        private final PasswordEncoder passwordEncoder;

        public RegisterController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
            this.userRepo = userRepo;
            this.passwordEncoder = passwordEncoder;
        }

        @GetMapping
        public String showRegistrationForm(User user) {
            return "register";
        }

        @PostMapping
        public String registerUser(User user, RedirectAttributes redirectAttributes) {

            if (userRepo.findByEmail(user.getEmail()).isPresent()) {
                redirectAttributes.addFlashAttribute("error", "Este email já está cadastrado.");
                return "redirect:/register";
            }

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            user.setRole(UserRole.USER);

            userRepo.save(user);

            redirectAttributes.addFlashAttribute("success", "Registro realizado com sucesso! Faça login.");
            return "redirect:/login";
        }
    }