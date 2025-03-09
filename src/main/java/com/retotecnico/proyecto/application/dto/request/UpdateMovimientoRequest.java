package com.retotecnico.proyecto.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateMovimientoRequest {
    private String movimientoId;
    private Date fecha;
    private Boolean tipo;
    private String cuentaId;
    private float valor;
}
