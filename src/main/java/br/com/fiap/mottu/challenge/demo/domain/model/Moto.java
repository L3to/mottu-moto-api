package br.com.fiap.mottu.challenge.demo.domain.model;

import br.com.fiap.mottu.challenge.demo.utils.StatusSensor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_CHMOTTU_MOTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O modelo é obrigatório.")
    @Size(max = 100)
    private String modelo;

    @NotBlank(message = "A placa é obrigatória.")
    @Column(unique = true)
    private String placa;

    @NotBlank(message = "O chassi é obrigatório.")
    @Column(unique = true)
    private String chassi;

    private boolean ativa;

    @Enumerated(EnumType.STRING)
    private StatusSensor statusSensor;

    @Embedded
    private Localizacao localizacaoAtual = new Localizacao();

    @ManyToOne
    @JoinColumn(name = "patio_id")
    @JsonBackReference
    private Patio patio;
}