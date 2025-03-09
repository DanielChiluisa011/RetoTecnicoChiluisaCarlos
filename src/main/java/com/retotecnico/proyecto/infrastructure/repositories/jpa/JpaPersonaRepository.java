package com.retotecnico.proyecto.infrastructure.repositories.jpa;

import com.retotecnico.proyecto.infrastructure.model.PersonaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPersonaRepository extends JpaRepository<PersonaModel, String> {
    Optional<PersonaModel> findById(String id);
    Optional<PersonaModel> findByIdentificacion(String identificacion);
}
