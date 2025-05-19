package br.com.fiap.mottu.challenge.demo.domain.model;

import jakarta.persistence.Embeddable;


@Embeddable
public class Localizacao {
    private String patio;
    private String vaga;

    public Localizacao(String patio, String vaga) {
        this.patio = patio;
        this.vaga = vaga;
    }

    public Localizacao() {

    }

    public String getPatio() {
        return patio;
    }

    public void setPatio(String patio) {
        this.patio = patio;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }
}
