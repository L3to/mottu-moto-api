package br.com.fiap.mottu.challenge.demo.controllers;

import br.com.fiap.mottu.challenge.demo.domain.model.Patio;
import br.com.fiap.mottu.challenge.demo.services.PatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/patios")
public class PatioController {

    @Autowired
    private PatioService patioService;

    /**
     * GET /patios
     * Exemplo: /patios?nome=Central&capacidadeMinima=50&page=0&size=10
     */
    @GetMapping
    public ResponseEntity<Page<Patio>> buscarPatios(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer capacidadeMaxima,
            @RequestParam(required = false) Integer capacidadeMinima,
            Pageable pageable) {
        Page<Patio> patios = patioService.buscarPatios(nome, capacidadeMaxima, capacidadeMinima, pageable);
        return ResponseEntity.ok(patios);
    }

    /**
     * GET /patios/{id}
     * Exemplo: /patios/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patio> buscarPorId(@PathVariable Long id) {
        Optional<Patio> patio = patioService.buscarPorId(id);
        return patio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /patios
     * Exemplo de corpo da requisição:
     * {
     *   "nome": "Central",
     *   "capacidadeMaxima": 100,
     *   "areaTotal": 500.0,
     *   "observacoes": "Pátio principal"
     * }
     */
    @PostMapping
    public ResponseEntity<Patio> salvar(@RequestBody Patio patio) {
        Patio patioSalvo = patioService.salvar(patio);
        return ResponseEntity.status(HttpStatus.CREATED).body(patioSalvo);
    }

    /**
     * PUT /patios/{id}
     * Exemplo: /patios/1
     * Exemplo de corpo da requisição:
     * {
     *   "nome": "Central Atualizado",
     *   "capacidadeMaxima": 150,
     *   "areaTotal": 600.0,
     *   "observacoes": "Atualização do pátio principal"
     * }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Patio> atualizar(@PathVariable Long id, @RequestBody Patio patioAtualizado) {
        try {
            Patio patio = patioService.atualizar(id, patioAtualizado);
            return ResponseEntity.ok(patio);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /patios/{id}
     * Exemplo: /patios/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            patioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}