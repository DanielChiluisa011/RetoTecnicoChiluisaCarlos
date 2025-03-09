package com.retotecnico.proyecto.application.command.delete;

import com.retotecnico.proyecto.domain.interfaces.IMovimientoRepository;
import io.github.jkratz55.mediator.core.*;
import org.springframework.stereotype.Component;

@Component
public class DeleteMovimientoCommandHandler implements RequestHandler<DeleteMovimientoCommand, String> {
    private final IMovimientoRepository movimientoRepository;
    public DeleteMovimientoCommandHandler(IMovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public String handle(DeleteMovimientoCommand deleteMovimientoCommand) {
        movimientoRepository.delete(deleteMovimientoCommand.getId());
        return deleteMovimientoCommand.getId();
    }
}
