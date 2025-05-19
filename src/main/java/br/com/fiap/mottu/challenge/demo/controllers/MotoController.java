package br.com.fiap.mottu.challenge.demo.controllers;

import br.com.fiap.mottu.challenge.demo.domain.dto.MotoFullDTO;
import br.com.fiap.mottu.challenge.demo.domain.model.StatusSensor;
import br.com.fiap.mottu.challenge.demo.services.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    /**
     * GET /motos
     * Exemplo de uso: /motos?modelo=Honda&statusSensor=ATIVADO&page=0&size=10&sort=modelo,asc
     * Parâmetros opcionais:
     * - modelo: Filtra motos pelo modelo (exemplo: "Honda").
     * - statusSensor: Filtra motos pelo status do sensor (exemplo: "ATIVADO").
     * - sort: Ordena os resultados (exemplo: "modelo,asc" ou "placa,desc").
     * - paginação: page (número da página) e size (tamanho da página).
     */
    @GetMapping
    public ResponseEntity<Page<MotoFullDTO>> buscarMotos(
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) StatusSensor statusSensor,
            @RequestParam(required = false) String sort,
            Pageable pageable) {
        Page<MotoFullDTO> motos = motoService.buscarMotos(modelo, statusSensor, pageable, sort);
        return ResponseEntity.ok(motos);
    }

    /**
     * GET /motos/{id}
     * Exemplo de uso: /motos/1
     * Retorna os detalhes de uma moto específica pelo ID.
     */

    @GetMapping("/{id}")
    public ResponseEntity<MotoFullDTO> buscarPorId(@PathVariable Long id) {
        Optional<MotoFullDTO> moto = motoService.buscarPorId(id);
        return moto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /motos
     * Exemplo de corpo da requisição:
     * {
     *   "modelo": "Honda",
     *   "placa": "ABC1234",
     *   "chassi": "1234567890",
     *   "ativa": true,
     *   "statusSensor": "ATIVADO",
     *   "localizacaoAtual": {
     *     "patio": "Pátio Central",
     *     "vaga": "A1"
     *   },
     *   "patio": {
     *     "id": 1
     *   }
     * }
     * Cria uma nova moto com os dados fornecidos.
     */
    @PostMapping
    public ResponseEntity<MotoFullDTO> salvar(@RequestBody MotoFullDTO motoFullDTO) {
        MotoFullDTO motoSalva = motoService.salvar(motoFullDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(motoSalva);
    }

    /**
     * PUT /motos/{id}
     * Exemplo de uso: /motos/1
     * Exemplo de corpo da requisição:
     * {
     *   "modelo": "Yamaha",
     *   "placa": "XYZ5678",
     *   "chassi": "0987654321",
     *   "ativa": false,
     *   "statusSensor": "MANUTENCAO",
     *   "localizacaoAtual": {
     *     "patio": "Pátio Norte",
     *     "vaga": "B2"
     *   },
     *   "patio": {
     *     "id": 2
     *   }
     * }
     * Atualiza os dados de uma moto existente pelo ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MotoFullDTO> atualizar(@PathVariable Long id, @RequestBody MotoFullDTO motoFullDTO) {
        try {
            MotoFullDTO motoAtualizada = motoService.atualizar(id, motoFullDTO);
            return ResponseEntity.ok(motoAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /motos/{id}
     * Exemplo de uso: /motos/1
     * Remove uma moto específica pelo ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            motoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}