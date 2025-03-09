package com.retotecnico.proyecto.infrastructure.mappers;

import com.retotecnico.proyecto.domain.entities.Cuenta;
import com.retotecnico.proyecto.infrastructure.model.CuentaModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    @Mapping(source="id", target="cuentaId")
    
    Cuenta toEntity(CuentaModel model);
    List<Cuenta> toEntityList(List<CuentaModel> listModels);
    @InheritInverseConfiguration
    CuentaModel toModel(Cuenta entity);
}
