package com.retotecnico.proyecto.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cuenta{
	private String clienteId;
    private String cuentaId;
    private String tipo;
    private String numero;
    private float saldoInicial;
    private Boolean estado;
}
