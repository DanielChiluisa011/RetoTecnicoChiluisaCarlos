package com.retotecnico.proyecto.application.command.delete;

import com.retotecnico.proyecto.domain.interfaces.IClienteRepository;
import io.github.jkratz55.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

@Component
public class DeleteClienteCommandHandler implements RequestHandler<DeleteClienteCommand, String> {
    private final IClienteRepository clienteRepository;
    public DeleteClienteCommandHandler(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public String handle(DeleteClienteCommand deleteClienteCommand) {
        clienteRepository.delete(deleteClienteCommand.getId());
        return deleteClienteCommand.getId();
    }
}
