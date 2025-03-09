package com.retotecnico.proyecto.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCuentaRequest {
    private String cuentaId;
    private String tipo;
    private String numero;
    private Boolean estado;
}
