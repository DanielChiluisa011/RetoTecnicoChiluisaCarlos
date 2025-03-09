package com.retotecnico.proyecto.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Movimiento {
	private String cuentaNumero;
    private String movimientoId;
    private Date fecha;
    private Boolean tipo;
    private float valor;
    private float saldo;
}
