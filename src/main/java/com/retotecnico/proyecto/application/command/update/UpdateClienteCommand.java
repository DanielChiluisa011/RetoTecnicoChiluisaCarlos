package com.retotecnico.proyecto.application.command.update;

import com.retotecnico.proyecto.application.dto.request.UpdateClienteRequest;
import io.github.jkratz55.mediator.core.*;

public class UpdateClienteCommand extends UpdateClienteRequest implements Command {
    public UpdateClienteCommand(){
        super();
    }
}
