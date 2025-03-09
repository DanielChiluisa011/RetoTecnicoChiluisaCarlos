package com.retotecnico.proyecto.application.command.read;

import com.retotecnico.proyecto.domain.entities.Movimiento;
import io.github.jkratz55.mediator.core.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadMovimientoCommand implements Request<Movimiento> {
    private String id;
    public ReadMovimientoCommand(String id){
        this.id = id;
    }
}
