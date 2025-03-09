package com.retotecnico.proyecto.infrastructure.repositories.jpa;

import com.retotecnico.proyecto.infrastructure.model.MovimientoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMovimientoRepository extends JpaRepository<MovimientoModel, String> {
    Optional<MovimientoModel> findById(String id);
    Optional<MovimientoModel> findFirstByOrderByFechaDesc();;
}
