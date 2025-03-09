package com.retotecnico.proyecto.application.command.read;


import com.retotecnico.proyecto.domain.entities.Movimiento;
import com.retotecnico.proyecto.domain.interfaces.IMovimientoRepository;
import io.github.jkratz55.mediator.core.*;
import org.springframework.stereotype.Component;

@Component
public class ReadMovimientoCommandHandler implements RequestHandler<ReadMovimientoCommand, Movimiento> {
    private final IMovimientoRepository movimientoRepository;

    public ReadMovimientoCommandHandler(IMovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public Movimiento handle(ReadMovimientoCommand readMovimientoCommand) {
        return movimientoRepository.getById(readMovimientoCommand.getId());
    }
}
