package br.com.fiap.mottu.challenge.demo.controllers;

import br.com.fiap.mottu.challenge.demo.domain.model.User;
import br.com.fiap.mottu.challenge.demo.repository.UserRepo;
import br.com.fiap.mottu.challenge.demo.utils.UserRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    private final UserRepo userRepo;

    public RoleController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "role";
    }



    @PostMapping("/toggleRole/{id}")
    public String toggleRole(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        return userRepo.findById(id).map(user -> {

            if (user.getRole() == UserRole.ADMIN && userRepo.countByRole(UserRole.ADMIN) == 1) {
                redirectAttributes.addFlashAttribute("error", "Não é possível rebaixar o único ADMIN do sistema.");
                return "redirect:/role";
            }

            if (user.getRole() == UserRole.ADMIN) {
                user.setRole(UserRole.USER);
                redirectAttributes.addFlashAttribute("success", "Usuário " + user.getEmail() + " rebaixado para USER.");
            } else {
                user.setRole(UserRole.ADMIN);
                redirectAttributes.addFlashAttribute("success", "Usuário " + user.getEmail() + " promovido para ADMIN!");
            }

            userRepo.save(user);
            return "redirect:/role";
        }).orElseGet(() -> {
            redirectAttributes.addFlashAttribute("error", "Usuário não encontrado.");
            return "redirect:/role";
        });
    }
}

