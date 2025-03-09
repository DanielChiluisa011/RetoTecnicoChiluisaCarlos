package com.retotecnico.proyecto.domain.interfaces;

import com.retotecnico.proyecto.domain.entities.Cuenta;

public interface ICuentaRepository {
	String save(Cuenta cuenta);
    Cuenta getById(String id);

    Cuenta getByNumero(String numero);
    void update(Cuenta cuenta);
    void delete(String id);
}
