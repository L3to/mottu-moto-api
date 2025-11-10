package br.com.fiap.mottu.challenge.demo.controllers;

import br.com.fiap.mottu.challenge.demo.domain.dto.MotoFullDTO;
import br.com.fiap.mottu.challenge.demo.utils.StatusSensor;
import br.com.fiap.mottu.challenge.demo.services.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    /**
     * GET /motos
     * Exemplo: /motos?modelo=Honda&statusSensor=ATIVADO&page=0&size=10
     */
    @GetMapping
    public String index(
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) StatusSensor statusSensor,
            @RequestParam(required = false) String sort,
            Pageable pageable,
            Model model) {
        Page<MotoFullDTO> motos = motoService.buscarMotos(modelo, statusSensor, pageable, sort);
        model.addAttribute("motos", motos);
        return "motos"; // View name
    }

    /**
     * GET /motos/{id}
     * Exemplo: /motos/1
     */
    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        Optional<MotoFullDTO> moto = motoService.buscarPorId(id);
        if (moto.isPresent()) {
            model.addAttribute("moto", moto.get());
            return "motos/detalhes"; // View for motorcycle details
        }
        return "redirect:/motos"; // Redirecting if not found
    }

    /**
     * GET /motos/cadastro
     * Formulário para cadastrar nova moto.
     */
    @GetMapping("/cadastro")
    public String formularioCadastro(Model model) {
        model.addAttribute("moto", new MotoFullDTO());
        return "motos/cadastro"; // View name for form
    }

    /**
     * POST /motos/cadastro
     * Salva a nova moto no banco e redireciona.
     */
    @PostMapping("/cadastro")
    public String salvar(
            @Valid @ModelAttribute MotoFullDTO motoFullDTO, Model model) {
        motoService.salvar(motoFullDTO);
        return "redirect:/motos";
    }

    /**
     * GET /motos/{id}/editar
     * Abre o formulário de edição de uma moto.
     */
    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Optional<MotoFullDTO> moto = motoService.buscarPorId(id);
        if (moto.isPresent()) {
            model.addAttribute("moto", moto.get());
            return "motos/edit";
        }
        return "redirect:/motos";
    }

    /**
     * POST /motos/{id}/editar
     * Processa a edição de uma moto.
     */
    @PostMapping("/{id}/editar")
    public String atualizar(
            @PathVariable Long id,
            @ModelAttribute MotoFullDTO motoFullDTO,
            Model model) {
        try {
            motoService.atualizar(id, motoFullDTO);
        } catch (RuntimeException e) {
            return "redirect:/motos";
        }
        return "redirect:/motos";
    }

    /**
     * GET /motos/{id}/deletar
     * Remove uma moto pelo ID.
     */
    @GetMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id, Model model) {
        try {
            motoService.deletar(id);
        } catch (RuntimeException e) {
            model.addAttribute("error", "Não foi possível deletar a moto.");
        }
        return "redirect:/motos";
    }
}