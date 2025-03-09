package com.retotecnico.proyecto.infrastructure.repositories.jpa;

import com.retotecnico.proyecto.infrastructure.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaClienteRepository extends JpaRepository<ClienteModel, String> {
    Optional<ClienteModel> findById(String id);
}
