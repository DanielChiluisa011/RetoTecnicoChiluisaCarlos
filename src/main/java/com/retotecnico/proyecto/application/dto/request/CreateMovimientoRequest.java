package com.retotecnico.proyecto.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class CreateMovimientoRequest {
    private Date fecha;
    private Boolean tipo;
    private float valor;
    @NotBlank(message = "cuenta field can't be empty.")
    private String cuentaNumero;
}
