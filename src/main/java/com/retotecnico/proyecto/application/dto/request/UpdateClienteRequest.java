package com.retotecnico.proyecto.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
@Getter
@Setter
public class UpdateClienteRequest {
    private String clienteId;
    @NotBlank(message = "Name field can't be empty.")
    private String nombre;

    @NotBlank(message = "dirección field can't be empty.")
    private String dirección;

    @NotBlank(message = "telefono field can't be empty.")
    private String telefono;

    @NotBlank(message = "contraseña field can't be empty.")
    private String contrasena;

    private int edad;

    @NotBlank(message = "genero field can't be empty.")
    private String genero;

    @NotBlank(message = "identificacion field can't be empty.")
    private String identificacion;

    private Boolean estado;


}
