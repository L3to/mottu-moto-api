package br.com.fiap.mottu.challenge.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_CHMOTTU_MOTO")
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

    public Moto() {}

    public Moto(Long id, String modelo, String placa, String chassi, boolean ativa, StatusSensor statusSensor, Localizacao localizacaoAtual, Patio patio) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.chassi = chassi;
        this.ativa = ativa;
        this.statusSensor = statusSensor;
        this.localizacaoAtual = localizacaoAtual;
        this.patio = patio;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

 public StatusSensor getStatusSensor() {
        return statusSensor;
    }

    public void setStatusSensor(StatusSensor statusSensor) {
        this.statusSensor = statusSensor;
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

}

