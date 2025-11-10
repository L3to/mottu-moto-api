package br.com.fiap.mottu.challenge.demo.controllers;

import br.com.fiap.mottu.challenge.demo.domain.model.Patio;
import br.com.fiap.mottu.challenge.demo.services.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/patios")
public class PatioController {

    @Autowired
    private PatioService patioService;


    @GetMapping
    public String index(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer capacidadeMaxima,
            @RequestParam(required = false) String sort, // Added sort parameter to mirror MotoController
            Pageable pageable,
            Model model) {

        Page<Patio> patios = patioService.buscarPatios(nome, capacidadeMaxima, pageable);
        model.addAttribute("patios", patios);
        return "patios";
    }

    /**
     * GET /patios/{id}
     * Exemplo: /patios/1
     */
    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        Optional<Patio> patio = patioService.buscarPorId(id);
        if (patio.isPresent()) {
            model.addAttribute("patio", patio.get());
            return "patios/detalhes";
        }
        return "redirect:/patios";
    }

    /**
     * GET /patios/cadastro
     * Formulário para cadastrar novo pátio.
     */
    @GetMapping("/cadastro")
    public String formularioCadastro(Model model) {
        model.addAttribute("patio", new Patio());
        return "patios/cadastro";
    }

    /**
     * POST /patios/cadastro
     * Salva o novo pátio no banco e redireciona.
     */
    @PostMapping("/cadastro")
    public String salvar(
            @Valid @ModelAttribute Patio patio, Model model) {

        patioService.salvar(patio);
        return "redirect:/patios";
    }

    /**
     * GET /patios/{id}/editar
     * Abre o formulário de edição de um pátio.
     */
    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Optional<Patio> patio = patioService.buscarPorId(id);
        if (patio.isPresent()) {
            model.addAttribute("patio", patio.get());
            return "patios/edit";
        }
        return "redirect:/patios";
    }

    /**
     * POST /patios/{id}/editar
     * Processa a edição de um pátio.
     */
    @PostMapping("/{id}/editar")
    public String atualizar(
            @PathVariable Long id,
            @ModelAttribute Patio patio,
            Model model) {
        try {
            patioService.atualizar(id, patio);
        } catch (RuntimeException e) {
            return "redirect:/patios";
        }
        return "redirect:/patios";
    }

    /**
     * GET /patios/{id}/deletar
     * Remove um pátio pelo ID (Acesso via link simples).
     */
    @GetMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id, Model model) {
        try {
            patioService.deletar(id);
        } catch (RuntimeException e) {
            model.addAttribute("error", "Não foi possível deletar o pátio.");
        }
        return "redirect:/patios";
    }
}
