package com.retotecnico.proyecto.application.command.create;

import com.retotecnico.proyecto.application.dto.response.CreateCuentaResponse;
import com.retotecnico.proyecto.application.dto.response.CreateMovimientoResponse;
import com.retotecnico.proyecto.domain.entities.Movimiento;
import com.retotecnico.proyecto.domain.interfaces.IMovimientoRepository;
import com.retotecnico.proyecto.infrastructure.model.MovimientoModel;

import io.github.jkratz55.mediator.core.Mediator;
import io.github.jkratz55.mediator.core.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
@Component
public class CreateMovimientoCommandHandler implements RequestHandler<CreateMovimientoCommand, CreateMovimientoResponse> {
    @Autowired
    private IMovimientoRepository movimientoRepository;

    @Override
    @Transactional
    public CreateMovimientoResponse handle(CreateMovimientoCommand createMovimientoCommand){
        var movimiento = new Movimiento();
        movimiento.setFecha(createMovimientoCommand.getFecha());
        movimiento.setTipo(createMovimientoCommand.getTipo());
        movimiento.setValor(createMovimientoCommand.getValor());
        movimiento.setCuentaNumero(createMovimientoCommand.getCuentaNumero());
        String movimientoId = movimientoRepository.save(movimiento);
        return new CreateMovimientoResponse(movimientoId);
    }
}

