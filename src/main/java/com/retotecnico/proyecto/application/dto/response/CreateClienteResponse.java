package com.retotecnico.proyecto.application.dto.response;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClienteResponse {
    private String clienteId;
    public CreateClienteResponse(String clienteId) {
        this.clienteId = clienteId;
    }
}
