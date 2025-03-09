package com.retotecnico.proyecto.application.command.create;

import com.retotecnico.proyecto.application.dto.request.CreateClienteRequest;
import com.retotecnico.proyecto.application.dto.response.CreateClienteResponse;
import com.retotecnico.proyecto.domain.entities.Cliente;
import com.retotecnico.proyecto.domain.interfaces.IClienteRepository;
import com.retotecnico.proyecto.infrastructure.model.ClienteModel;

import io.github.jkratz55.mediator.core.Mediator;
import io.github.jkratz55.mediator.core.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class CreateClienteCommandHandler implements RequestHandler<CreateClienteCommand, CreateClienteResponse> {
    @Autowired
    private IClienteRepository clienteRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreateClienteCommandHandler.class);

    @Override
    @Transactional
    public CreateClienteResponse handle(CreateClienteCommand createClienteCommand){
        var cliente = new Cliente();
        cliente.setNombre(createClienteCommand.getNombre());
        cliente.setDireccion(createClienteCommand.getDirección());
        cliente.setTelefono(createClienteCommand.getTelefono());
        cliente.setIdentificacion(createClienteCommand.getIdentificacion());
        cliente.setGenero(createClienteCommand.getGenero());
        cliente.setEdad(createClienteCommand.getEdad());
        cliente.setContrasena(createClienteCommand.getContrasena());
        cliente.setEstado(createClienteCommand.getEstado());
        String clientId = clienteRepository.save(cliente);
        return new CreateClienteResponse(clientId);
    }
}
