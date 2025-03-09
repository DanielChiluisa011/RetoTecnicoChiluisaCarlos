package com.retotecnico.proyecto.application.command.create;


import com.retotecnico.proyecto.application.dto.request.CreateCuentaRequest;
import com.retotecnico.proyecto.application.dto.response.CreateCuentaResponse;
import io.github.jkratz55.mediator.core.*;

public class CreateCuentaCommand extends CreateCuentaRequest implements Request<CreateCuentaResponse> {

    public CreateCuentaCommand(){
        super();
    }
}
