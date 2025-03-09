package com.retotecnico.proyecto.application.command.create;

import com.retotecnico.proyecto.application.dto.response.CreateCuentaResponse;
import com.retotecnico.proyecto.domain.entities.Cuenta;
import com.retotecnico.proyecto.domain.interfaces.ICuentaRepository;
import com.retotecnico.proyecto.infrastructure.model.CuentaModel;

import io.github.jkratz55.mediator.core.Mediator;
import io.github.jkratz55.mediator.core.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
@Component
public class CreateCuentaCommandHanlder implements RequestHandler<CreateCuentaCommand, CreateCuentaResponse> {
    @Autowired
    private ICuentaRepository cuentaRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreateCuentaCommandHanlder.class);

    @Override
    @Transactional
    public CreateCuentaResponse handle(CreateCuentaCommand createCuentaCommand){
        var cuenta = new Cuenta();
        cuenta.setTipo(createCuentaCommand.getTipo());
        cuenta.setSaldoInicial(createCuentaCommand.getSaldoInicial());
        cuenta.setNumero(createCuentaCommand.getNumero());
        cuenta.setEstado(createCuentaCommand.getEstado());
        cuenta.setClienteId(createCuentaCommand.getClienteId());
        String cuentaId = cuentaRepository.save(cuenta);
        return new CreateCuentaResponse(cuentaId);
    }
}
