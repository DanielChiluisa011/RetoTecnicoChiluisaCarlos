package com.retotecnico.proyecto.application.command.update;

import com.retotecnico.proyecto.application.dto.request.UpdateMovimientoRequest;
import io.github.jkratz55.mediator.core.*;
public class UpdateMovimientoCommand extends UpdateMovimientoRequest implements Command {
    public UpdateMovimientoCommand(){
        super();
    }
}
