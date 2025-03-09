package com.retotecnico.proyecto.application.command.create;

import com.retotecnico.proyecto.application.dto.request.CreateClienteRequest;
import com.retotecnico.proyecto.application.dto.response.CreateClienteResponse;
import io.github.jkratz55.mediator.core.Request;

public class CreateClienteCommand extends CreateClienteRequest implements Request<CreateClienteResponse> {

    public CreateClienteCommand(){
        super();
    }
}
