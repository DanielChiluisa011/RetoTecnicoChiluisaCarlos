package com.retotecnico.proyecto.infrastructure.repositories.jpa;

import com.retotecnico.proyecto.infrastructure.model.CuentaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCuentaRepository extends JpaRepository<CuentaModel, String> {
    Optional<CuentaModel> findById(String id);
    Optional<CuentaModel> findByNumero(String numero);
}
