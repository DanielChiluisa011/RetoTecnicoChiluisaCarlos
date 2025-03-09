package com.retotecnico.proyecto.application.command.update;

import com.retotecnico.proyecto.application.dto.request.UpdateCuentaRequest;
import io.github.jkratz55.mediator.core.*;

public class UpdateCuentaCommand extends UpdateCuentaRequest implements Command {
    public UpdateCuentaCommand(){
        super();
    }
}
