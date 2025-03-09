package com.retotecnico.proyecto.domain.interfaces;

import com.retotecnico.proyecto.domain.entities.Persona;

public interface IPersonaRepository {
	String save(Persona persona);
	Persona getById(String id);
	Persona getByIdentificacion(String identificacion);
    void update(Persona cliente);
    void delete(String id);
}
