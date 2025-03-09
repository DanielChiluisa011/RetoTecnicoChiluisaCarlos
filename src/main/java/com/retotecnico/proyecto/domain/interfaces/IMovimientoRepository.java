package com.retotecnico.proyecto.domain.interfaces;

import com.retotecnico.proyecto.domain.entities.Movimiento;

public interface IMovimientoRepository {
	String save(Movimiento movimiento);
    Movimiento getById(String id);
    void update(Movimiento movimiento);
    void delete(String id);
}
