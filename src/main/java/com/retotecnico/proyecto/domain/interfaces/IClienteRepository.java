package com.retotecnico.proyecto.domain.interfaces;

import com.retotecnico.proyecto.domain.entities.Cliente;

public interface IClienteRepository {
	String save(Cliente cliente);
    Cliente getById(String id);
    void update(Cliente cliente);
    void delete(String id);
}
