package com.retotecnico.proyecto.application.command.update;

import com.retotecnico.proyecto.domain.entities.Cliente;
import com.retotecnico.proyecto.domain.interfaces.IClienteRepository;
import io.github.jkratz55.mediator.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class UpdateClienteCommandHandler implements CommandHandler<UpdateClienteCommand> {
    private static final Logger logger = LoggerFactory.getLogger(UpdateClienteCommandHandler.class);
    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    @Transactional
    public void handle(UpdateClienteCommand updateClienteCommand){
        var cliente = getCliente(updateClienteCommand.getClienteId());
        cliente.setEstado(updateClienteCommand.getEstado());
        cliente.setContrasena(updateClienteCommand.getContrasena());
        cliente.setNombre(updateClienteCommand.getNombre());
        cliente.setTelefono(updateClienteCommand.getTelefono());
        cliente.setDireccion(updateClienteCommand.getDirección());
        cliente.setIdentificacion(updateClienteCommand.getIdentificacion());
        cliente.setEdad(updateClienteCommand.getEdad());
        cliente.setGenero(updateClienteCommand.getGenero());
        clienteRepository.update(cliente);
    }

    private Cliente getCliente(String id){
        return clienteRepository.getById(id);
    }
}
