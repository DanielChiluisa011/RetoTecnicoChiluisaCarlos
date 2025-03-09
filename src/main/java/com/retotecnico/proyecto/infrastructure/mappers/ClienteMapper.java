package com.retotecnico.proyecto.infrastructure.mappers;

import com.retotecnico.proyecto.domain.entities.Cliente;
import com.retotecnico.proyecto.infrastructure.model.ClienteModel;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	@Mapping(source="id", target="clienteId")
	Cliente toEntity(ClienteModel model);
    List<Cliente> toEntityList(List<ClienteModel> listModels);
    @InheritInverseConfiguration
    ClienteModel toModel(Cliente model);
    
}
