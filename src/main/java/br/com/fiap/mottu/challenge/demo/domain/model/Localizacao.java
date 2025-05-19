package br.com.fiap.mottu.challenge.demo.domain.model;

import jakarta.persistence.Embeddable;


@Embeddable
public class Localizacao {
    private String andar;
    private String vaga;

    public Localizacao(String andar, String vaga) {
        this.andar = andar;
        this.vaga = vaga;
    }

    public Localizacao() {

    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String patio) {
        this.andar = patio;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }
}
