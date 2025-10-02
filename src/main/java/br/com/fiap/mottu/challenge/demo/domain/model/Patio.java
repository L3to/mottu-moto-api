package br.com.fiap.mottu.challenge.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TB_CHMOTTU_PATIO")
@Getter
@Setter
@NoArgsConstructor
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do pátio é obrigatório.")
    private String nome;

    @Min(1)
    private int capacidadeMaxima;

    @Positive(message = "A área total deve ser positiva.")
    private double areaTotal;

    @Column(length = 1000)
    @Size(max = 1000, message = "A observação deve ter no máximo 1000 caracteres.")
    private String observacoes;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Moto> motos;

    public Patio(String nome, int capacidadeMaxima, double areaTotal, String observacoes) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.areaTotal = areaTotal;
        this.observacoes = observacoes;
    }
}