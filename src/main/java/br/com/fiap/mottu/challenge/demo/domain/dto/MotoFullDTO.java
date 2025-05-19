package br.com.fiap.mottu.challenge.demo.domain.dto;

import br.com.fiap.mottu.challenge.demo.domain.model.Localizacao;
import br.com.fiap.mottu.challenge.demo.domain.model.Moto;
import br.com.fiap.mottu.challenge.demo.domain.model.StatusSensor;

public class MotoFullDTO {

    private Long id;
    private String modelo;
    private String placa;
    private String chassi;
    private boolean ativa;
    private StatusSensor statusSensor;
    private Localizacao localizacaoAtual;
    private PatioReducedDTO patio;

    public MotoFullDTO() {}

    public MotoFullDTO(Moto moto) {
        this.id = moto.getId();
        this.modelo = moto.getModelo();
        this.placa = moto.getPlaca();
        this.chassi = moto.getChassi();
        this.ativa = moto.isAtiva();
        this.statusSensor = moto.getStatusSensor();
        this.localizacaoAtual = moto.getLocalizacaoAtual();
        if (moto.getPatio() != null) {
            this.patio = new PatioReducedDTO(moto.getPatio().getId(), moto.getPatio().getNome());
        }
    }

    public Moto toEntity() {
        Moto moto = new Moto();
        moto.setId(this.id);
        moto.setModelo(this.modelo);
        moto.setPlaca(this.placa);
        moto.setChassi(this.chassi);
        moto.setAtiva(this.ativa);
        moto.setStatusSensor(this.statusSensor);
        moto.setLocalizacaoAtual(this.localizacaoAtual);

        if (this.patio != null) {
            // Mapeamento mínimo para referência do pátio pela ID
            var patioEntity = new br.com.fiap.mottu.challenge.demo.domain.model.Patio();
            patioEntity.setId(this.patio.getId());
            moto.setPatio(patioEntity);
        }

        return moto;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getChassi() { return chassi; }
    public void setChassi(String chassi) { this.chassi = chassi; }

    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }

    public StatusSensor getStatusSensor() { return statusSensor; }
    public void setStatusSensor(StatusSensor statusSensor) { this.statusSensor = statusSensor; }

    public Localizacao getLocalizacaoAtual() { return localizacaoAtual; }
    public void setLocalizacaoAtual(Localizacao localizacaoAtual) { this.localizacaoAtual = localizacaoAtual; }

    public PatioReducedDTO getPatio() { return patio; }
    public void setPatio(PatioReducedDTO patio) { this.patio = patio; }
}
