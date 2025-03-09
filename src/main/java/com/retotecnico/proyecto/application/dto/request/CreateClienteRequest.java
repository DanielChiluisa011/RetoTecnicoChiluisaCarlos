package com.retotecnico.proyecto.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CreateClienteRequest {
    @NotBlank(message = "Name field can't be empty.")
    private String nombre;

    @NotBlank(message = "dirección field can't be empty.")
    private String dirección;

    @NotBlank(message = "telefono field can't be empty.")
    private String telefono;

    @NotBlank(message = "género field can't be empty.")
    private String genero;
    
    @NotNull(message = "edad field can't be null")
    private int edad;
    
    @NotBlank(message = "identificacion field can't be empty.")
    private String identificacion;
    
    @NotBlank(message = "contraseña field can't be empty.")
    private String contrasena;

    private Boolean estado;

}
