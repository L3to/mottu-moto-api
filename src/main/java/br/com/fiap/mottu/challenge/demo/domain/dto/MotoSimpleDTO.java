package br.com.fiap.mottu.challenge.demo.domain.dto;

import br.com.fiap.mottu.challenge.demo.domain.model.Moto;
import br.com.fiap.mottu.challenge.demo.utils.StatusSensor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MotoSimpleDTO {
    private Long id;
    private String modelo;
    private String placa;
    private String chassi;
    private boolean ativa;
    private StatusSensor statusSensor;

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
}