package com.retotecnico.proyecto.application.command.delete;

import io.github.jkratz55.mediator.core.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteMovimientoCommand implements Request<String> {
    private String id;
    public DeleteMovimientoCommand(String id){
        this.id = id;
    }
}
