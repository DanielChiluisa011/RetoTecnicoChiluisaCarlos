package com.retotecnico.proyecto.api.controller;


import com.retotecnico.proyecto.application.command.create.CreateMovimientoCommand;
import com.retotecnico.proyecto.application.command.delete.DeleteMovimientoCommand;
import com.retotecnico.proyecto.application.command.read.ReadMovimientoCommand;
import com.retotecnico.proyecto.application.command.update.UpdateMovimientoCommand;
import com.retotecnico.proyecto.application.dto.response.CreateClienteResponse;
import com.retotecnico.proyecto.application.dto.response.CreateMovimientoResponse;
import com.retotecnico.proyecto.application.util.Shared;
import com.retotecnico.proyecto.domain.entities.Movimiento;
import io.github.jkratz55.mediator.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/movimientos")
public class MovimientosController {
    private static final Logger logger = LoggerFactory.getLogger(MovimientosController.class);
    public static final String SENDING_COMMAND = "------ Sending command : {} {} ";
    public static final String SENDING_QUERY = "------ Sending query in {}: {} {}";
    private final Mediator mediator;

    public MovimientosController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/")
    public ResponseEntity<CreateMovimientoResponse> createMovimiento(@Valid @RequestBody CreateMovimientoCommand command) {
        try{
            CreateMovimientoCommand safeCommand = new CreateMovimientoCommand();
            safeCommand.setTipo(command.getTipo());
            safeCommand.setFecha(command.getFecha());
            safeCommand.setValor(command.getValor());
            safeCommand.setCuentaNumero(command.getCuentaNumero());
            return new ResponseEntity<>(this.mediator.dispatch(safeCommand), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            logger.error("Error en la solicitud: " + e.getReason());
            return ResponseEntity.status(e.getStatusCode())
                    .body(new CreateMovimientoResponse("Error: " + e.getReason()));
        } catch (Exception e) {
            logger.error("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CreateMovimientoResponse(e.getMessage()));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getCuenta(@PathVariable("id") String id) {
        try {
            var query = new ReadMovimientoCommand(id);
            return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            logger.info(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/")
    public ResponseEntity<Void> putMovimiento(@Valid @RequestBody UpdateMovimientoCommand command) {
        try{
            UpdateMovimientoCommand safeCommand = new UpdateMovimientoCommand();
            safeCommand.setMovimientoId(command.getMovimientoId());
            safeCommand.setTipo(command.getTipo());
            safeCommand.setValor(command.getValor());
            safeCommand.setFecha(command.getFecha());
            safeCommand.setCuentaId(command.getCuentaId());
            this.mediator.dispatch(command);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (
                ResponseStatusException e) {
            logger.info(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable("id") String id) {
        try {
            var query = new DeleteMovimientoCommand(id);
            this.mediator.dispatch(query);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ResponseStatusException e) {
            logger.info(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
