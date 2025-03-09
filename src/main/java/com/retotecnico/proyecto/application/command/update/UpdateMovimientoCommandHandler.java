package com.retotecnico.proyecto.application.command.update;

import com.retotecnico.proyecto.domain.entities.Cuenta;
import com.retotecnico.proyecto.domain.entities.Movimiento;
import com.retotecnico.proyecto.domain.interfaces.ICuentaRepository;
import com.retotecnico.proyecto.domain.interfaces.IMovimientoRepository;
import io.github.jkratz55.mediator.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;

public class UpdateMovimientoCommandHandler implements CommandHandler<UpdateMovimientoCommand> {
    private static final Logger logger = LoggerFactory.getLogger(UpdateMovimientoCommandHandler.class);
    @Autowired
    private IMovimientoRepository movimientoRepository;

    @Override
    @Transactional
    public void handle(UpdateMovimientoCommand updateMovimientoCommand){
        var movimiento = getMovimiento(updateMovimientoCommand.getMovimientoId());
        movimiento.setTipo(updateMovimientoCommand.getTipo());
        movimiento.setValor(updateMovimientoCommand.getValor());
        movimiento.setFecha(updateMovimientoCommand.getFecha());
        movimientoRepository.update(movimiento);
    }

    private Movimiento getMovimiento(String id){
        return movimientoRepository.getById(id);
    }
}
