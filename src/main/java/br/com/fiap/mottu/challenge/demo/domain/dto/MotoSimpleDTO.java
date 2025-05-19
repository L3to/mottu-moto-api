package br.com.fiap.mottu.challenge.demo.domain.dto;

import br.com.fiap.mottu.challenge.demo.domain.model.Moto;
import br.com.fiap.mottu.challenge.demo.domain.model.StatusSensor;

public class MotoSimpleDTO {

    private Long id;
    private String modelo;
    private String placa;
    private String chassi;
    private boolean ativa;
    private StatusSensor statusSensor;

    public MotoSimpleDTO() {}

    public MotoSimpleDTO(Moto moto) {
        this.id = moto.getId();
        this.modelo = moto.getModelo();
        this.placa = moto.getPlaca();
        this.chassi = moto.getChassi();
        this.ativa = moto.isAtiva();
        this.statusSensor = moto.getStatusSensor();
    }

    public Moto toEntity() {
        Moto moto = new Moto();
        moto.setId(this.id);
        moto.setModelo(this.modelo);
        moto.setPlaca(this.placa);
        moto.setChassi(this.chassi);
        moto.setAtiva(this.ativa);
        moto.setStatusSensor(this.statusSensor);
        return moto;
    }

    // Getters e Setters
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
}