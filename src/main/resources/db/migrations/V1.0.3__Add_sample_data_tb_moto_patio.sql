INSERT INTO TB_CHMOTTU_PATIO (nome, capacidade_maxima, area_total, observacoes) VALUES
    ('Pátio Central SP', 500, 1000.50, 'Principal hub de distribuição. Monitoramento 24h.');

INSERT INTO TB_CHMOTTU_MOTO (
    modelo,
    placa,
    chassi,
    ativa,
    status_sensor,
    andar,
    vaga,
    localizacao_atual_latitude,
    localizacao_atual_longitude,
    patio_id
)
VALUES
    (
        'Honda Biz 125',
        'ABC1234',
        'CHASSI12345678901234',
        1,
        'PARKED',
        '2º Andar',
        'Vaga A15',
        -23.5505,
        -46.6333,
        1
    );