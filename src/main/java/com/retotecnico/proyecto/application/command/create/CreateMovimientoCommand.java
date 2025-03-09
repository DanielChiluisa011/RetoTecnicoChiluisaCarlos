package com.retotecnico.proyecto.application.command.create;

import com.retotecnico.proyecto.application.dto.request.CreateMovimientoRequest;
import com.retotecnico.proyecto.application.dto.response.CreateMovimientoResponse;
import io.github.jkratz55.mediator.core.Request;

public class CreateMovimientoCommand  extends CreateMovimientoRequest implements Request<CreateMovimientoResponse> {

    public CreateMovimientoCommand(){
        super();
    }
}
