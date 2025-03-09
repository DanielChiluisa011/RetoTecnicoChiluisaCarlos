package com.retotecnico.proyecto.domain.entities;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Persona {
	private String personaId;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
