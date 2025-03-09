package com.retotecnico.proyecto.api.controller;

import com.retotecnico.proyecto.application.command.create.CreateCuentaCommand;
import com.retotecnico.proyecto.application.command.delete.DeleteCuentaCommand;
import com.retotecnico.proyecto.application.command.read.ReadCuentaCommand;
import com.retotecnico.proyecto.application.command.update.UpdateCuentaCommand;
import com.retotecnico.proyecto.application.dto.response.CreateClienteResponse;
import com.retotecnico.proyecto.application.dto.response.CreateCuentaResponse;
import com.retotecnico.proyecto.application.util.Shared;
import com.retotecnico.proyecto.domain.entities.Cuenta;
import io.github.jkratz55.mediator.core.Mediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cuentas")
public class CuentasController {
    private static final Logger logger = LoggerFactory.getLogger(CuentasController.class);
    public static final String SENDING_COMMAND = "------ Sending command : {} {} ";
    public static final String SENDING_QUERY = "------ Sending query in {}: {} {}";

    private final Mediator mediator;
    public CuentasController(Mediator mediator) {
        this.mediator = mediator;
    }
    @PostMapping("/")
    public ResponseEntity<CreateCuentaResponse> createCuenta(@Valid @RequestBody CreateCuentaCommand command) {
        try{
            CreateCuentaCommand safeCommand = new CreateCuentaCommand();
            logger.info("sanitize {}",Shared. sanitizeString(command.getTipo()));
            safeCommand.setTipo(Shared.sanitizeString(command.getTipo()));
            safeCommand.setNumero(Shared.sanitizeString(command.getNumero()));
            safeCommand.setClienteId(Shared.sanitizeString(command.getClienteId()));
            safeCommand.setEstado(command.getEstado());
            safeCommand.setSaldoInicial(command.getSaldoInicial());
            return new ResponseEntity<>(this.mediator.dispatch(safeCommand), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            logger.error("Error en la solicitud: " + e.getReason());
            return ResponseEntity.status(e.getStatusCode())
                    .body(new CreateCuentaResponse("Error: " + e.getReason()));
        } catch (Exception e) {
            logger.error("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreateCuentaResponse(e.getMessage()));
        }
    }
    @GetMapping("/{numero}")
    public ResponseEntity<?> getCuenta(@PathVariable String numero) {
        try {
            var query = new ReadCuentaCommand(Shared.sanitizeString(numero));
            return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            logger.error("Error al consultar cuentas: {}", e.getMessage(), e);
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/")
    public ResponseEntity<Void> putCuenta(@Valid @RequestBody UpdateCuentaCommand command) {
        try{
            UpdateCuentaCommand safeCommand = new UpdateCuentaCommand();
            safeCommand.setCuentaId(command.getCuentaId());
            safeCommand.setEstado(command.getEstado());
            safeCommand.setNumero(command.getNumero());
            safeCommand.setTipo(command.getTipo());
            this.mediator.dispatch(command);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (
                ResponseStatusException e) {
            logger.info(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable("id") String id) {
        try {
            var query = new DeleteCuentaCommand(id);
            this.mediator.dispatch(query);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResponseStatusException e) {
            logger.error("Error al consultar cuentas: {}", e.getMessage(), e);
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
