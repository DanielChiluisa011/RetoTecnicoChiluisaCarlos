package com.retotecnico.proyecto.application.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCuentaResponse {
    private String cuentaId;
    public CreateCuentaResponse(String cuentaId) {
        this.cuentaId = cuentaId;
    }
}
