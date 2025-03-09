package com.retotecnico.proyecto.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente extends Persona{
    private String clienteId;
    private String personaId;
    private String contrasena;
    private Boolean estado;

    public Cliente(){
        super();
    }
}
