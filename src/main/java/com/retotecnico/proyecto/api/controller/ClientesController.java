package com.retotecnico.proyecto.api.controller;

import com.retotecnico.proyecto.application.command.create.CreateClienteCommand;
import com.retotecnico.proyecto.application.command.delete.DeleteClienteCommand;
import com.retotecnico.proyecto.application.command.read.ReadClientesCommand;
import com.retotecnico.proyecto.application.command.update.UpdateClienteCommand;
import com.retotecnico.proyecto.application.dto.response.CreateClienteResponse;
import com.retotecnico.proyecto.application.util.Shared;

import io.github.jkratz55.mediator.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
    private static final Logger logger = LoggerFactory.getLogger(CuentasController.class);
    public static final String SENDING_COMMAND = "------ Sending command : {} {} ";
    public static final String SENDING_QUERY = "------ Sending query in {}: {} {}";

    @Autowired
    private final Mediator mediator;
    
    public ClientesController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/")
    public ResponseEntity<CreateClienteResponse> postCliente(@Valid @RequestBody CreateClienteCommand command) {
        try{
            CreateClienteCommand safeCommand = new CreateClienteCommand();
            safeCommand.setNombre(Shared.sanitizeString(command.getNombre()));
            safeCommand.setDirección(Shared.sanitizeString(command.getDirección()));
            safeCommand.setTelefono(Shared.sanitizeString(command.getTelefono()));
            safeCommand.setEdad(command.getEdad());
            safeCommand.setGenero(command.getGenero());
            safeCommand.setIdentificacion(Shared.sanitizeString(command.getIdentificacion()));
            safeCommand.setContrasena(Shared.sanitizeString(command.getContrasena()));
            safeCommand.setEstado(command.getEstado());
            return new ResponseEntity<>(this.mediator.dispatch(safeCommand), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            logger.error("Error en la solicitud: " + e.getReason());
            return ResponseEntity.status(e.getStatusCode())
                    .body(new CreateClienteResponse("Error: " + e.getReason()));
        } catch (Exception e) {
            logger.error("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreateClienteResponse(e.getMessage()));
        }
    }
    @PutMapping("/")
    public ResponseEntity<Void> putCliente(@Valid @RequestBody UpdateClienteCommand command) {
        try{
            UpdateClienteCommand safeCommand = new UpdateClienteCommand();
            safeCommand.setNombre(Shared.sanitizeString(command.getNombre()));
            safeCommand.setDirección(Shared.sanitizeString(command.getDirección()));
            safeCommand.setTelefono(Shared.sanitizeString(command.getTelefono()));
            safeCommand.setContrasena(Shared.sanitizeString(command.getContrasena()));
            safeCommand.setIdentificacion(Shared.sanitizeString(command.getIdentificacion()));
            safeCommand.setEdad(command.getEdad());
            safeCommand.setGenero(Shared.sanitizeString(command.getGenero()));
            safeCommand.setEstado(command.getEstado());
            this.mediator.dispatch(command);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResponseStatusException e) {
            logger.info(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCliente(@PathVariable("id") String id) {
        try {
            var query = new ReadClientesCommand(id);
            return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            logger.error("Error al consultar clientes: {}", e.getMessage(), e);
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable("id") String id) {
        try {
            var query = new DeleteClienteCommand(id);
            this.mediator.dispatch(query);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResponseStatusException e) {
            logger.error("Error al consultar clientes: {}", e.getMessage(), e);
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
