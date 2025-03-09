package com.retotecnico.proyecto.application.command.read;

import com.retotecnico.proyecto.domain.entities.Cuenta;
import com.retotecnico.proyecto.domain.interfaces.ICuentaRepository;
import io.github.jkratz55.mediator.core.*;
import org.springframework.stereotype.Component;

@Component
public class ReadCuentaCommandHandler implements RequestHandler<ReadCuentaCommand, Cuenta> {
    private final ICuentaRepository cuentaRepository;

    public ReadCuentaCommandHandler(ICuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public Cuenta handle(ReadCuentaCommand readCuentaCommand) {
        return cuentaRepository.getByNumero(readCuentaCommand.getNumero());
    }
}