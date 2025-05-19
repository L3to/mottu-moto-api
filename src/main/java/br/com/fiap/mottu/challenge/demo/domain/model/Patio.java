package br.com.fiap.mottu.challenge.demo.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "TB_CHMOTTU_PATIO")
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
    private List<Moto> motos;

    public Patio() {}

    public Patio(String nome, int capacidadeMaxima, double areaTotal, String observacoes) {
        this.nome = nome;
        this.capacidadeMaxima = capacidadeMaxima;
        this.areaTotal = areaTotal;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public List<Moto> getMotos() {
        return motos;
    }

    public void setMotos(List<Moto> motos) {
        this.motos = motos;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
