package com.retotecnico.proyecto.application.command.delete;

import io.github.jkratz55.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteClienteCommand implements Request<String> {
    private String id;
    public DeleteClienteCommand(String id){
        this.id = id;
    }
}
