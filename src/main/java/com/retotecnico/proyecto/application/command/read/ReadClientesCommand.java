package com.retotecnico.proyecto.application.command.read;

import com.retotecnico.proyecto.domain.entities.Cliente;
import io.github.jkratz55.mediator.core.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadClientesCommand implements Request<Cliente> {
    private String id;
    public ReadClientesCommand(String id){
        this.id = id;
    }
}
