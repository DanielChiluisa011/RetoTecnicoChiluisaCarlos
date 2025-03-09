package com.retotecnico.proyecto.infrastructure.mappers;

import com.retotecnico.proyecto.domain.entities.Persona;
import com.retotecnico.proyecto.infrastructure.model.PersonaModel;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
	@Mapping(source="id", target="personaId")
	Persona toEntity(PersonaModel model);
    List<Persona> toEntityList(List<PersonaModel> listModels);
    @InheritInverseConfiguration
    PersonaModel toModel(Persona model);
    
}
