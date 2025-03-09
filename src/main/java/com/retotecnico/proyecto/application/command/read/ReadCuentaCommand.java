package com.retotecnico.proyecto.application.command.read;

import com.retotecnico.proyecto.domain.entities.Cuenta;
import io.github.jkratz55.mediator.core.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadCuentaCommand implements Request<Cuenta> {
    private String numero;
    public ReadCuentaCommand(String numero){
        this.numero = numero;
    }
}
