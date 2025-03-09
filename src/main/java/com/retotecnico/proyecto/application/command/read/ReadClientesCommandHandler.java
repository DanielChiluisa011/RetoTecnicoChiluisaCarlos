package com.retotecnico.proyecto.application.command.read;

import com.retotecnico.proyecto.domain.entities.Cliente;
import com.retotecnico.proyecto.domain.interfaces.IClienteRepository;

import io.github.jkratz55.mediator.core.RequestHandler;

import org.springframework.stereotype.Component;

@Component
public class ReadClientesCommandHandler implements RequestHandler<ReadClientesCommand, Cliente> {
    private final IClienteRepository clienteRepository;
    public ReadClientesCommandHandler(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente handle(ReadClientesCommand readClientesCommand) {
        return clienteRepository.getById(readClientesCommand.getId());
    }
}
