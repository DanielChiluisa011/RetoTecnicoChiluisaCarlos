package com.retotecnico.proyecto.application.command.delete;

import com.retotecnico.proyecto.domain.interfaces.ICuentaRepository;
import io.github.jkratz55.mediator.core.*;
import org.springframework.stereotype.Component;

@Component
public class DeleteCuentaCommandHandler implements RequestHandler<DeleteCuentaCommand, String> {
    private final ICuentaRepository cuentaRepository;
    public DeleteCuentaCommandHandler(ICuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public String handle(DeleteCuentaCommand deleteCuentaCommand) {
        cuentaRepository.delete(deleteCuentaCommand.getId());
        return deleteCuentaCommand.getId();
    }
}
