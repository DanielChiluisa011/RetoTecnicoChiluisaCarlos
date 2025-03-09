package com.retotecnico.proyecto.infrastructure.repositories;

import com.retotecnico.proyecto.domain.entities.Movimiento;
import com.retotecnico.proyecto.domain.interfaces.IMovimientoRepository;
import com.retotecnico.proyecto.exceptions.MessageService;
import com.retotecnico.proyecto.exceptions.ServiceException;
import com.retotecnico.proyecto.infrastructure.mappers.CuentaMapper;
import com.retotecnico.proyecto.infrastructure.mappers.MovimientoMapper;
import com.retotecnico.proyecto.infrastructure.model.CuentaModel;
import com.retotecnico.proyecto.infrastructure.model.MovimientoModel;
import com.retotecnico.proyecto.infrastructure.repositories.jpa.JpaCuentaRepository;
import com.retotecnico.proyecto.infrastructure.repositories.jpa.JpaMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class MovimientoRepository implements IMovimientoRepository {
    public static final String SENDING_COMMAND = "------ Sending command: {} {}";
    private static final Logger logger = LoggerFactory.getLogger(MovimientoRepository.class);
    @Autowired
    private JpaMovimientoRepository jpaMovimientoRepository;
    @Autowired
    private JpaCuentaRepository jpaCuentaRepository;
    @Autowired
    private MovimientoMapper mapper;
    @Autowired
    private MessageService messageService;


    @Override
    public String save(Movimiento movimiento){
    	try {
    		CuentaModel cuenta = jpaCuentaRepository.findByNumero(movimiento.getCuentaNumero()).orElse(null);
       	 if(cuenta == null) {
       		 throw new ServiceException(messageService.getMessage("error.cuenta.get"), null);
       	 }
       	 MovimientoModel movimientoModel = mapper.toModel(movimiento);
       	 movimientoModel.setCuentaId(cuenta.getId());
       	 MovimientoModel lastMovimientoModel = jpaMovimientoRepository.findFirstByOrderByFechaDesc().orElse(null);
       	 if(movimientoModel.getTipo()) { 
       		
       		movimientoModel.setSaldo(lastMovimientoModel.getSaldo() + movimiento.getValor());
       	 }else {
       		if(lastMovimientoModel.getSaldo() < movimiento.getValor()) {
       			throw new ServiceException(messageService.getMessage("error.movimeinto.saldo.insuficiente"), null);
       		}
       		movimientoModel.setSaldo(lastMovimientoModel.getSaldo() - movimiento.getValor());
       	 }
           return this.jpaMovimientoRepository.save(movimientoModel).getId();
    	}catch (ServiceException e) {
            logger.warn("Error al guardar cuenta: {}", e.getMessage());
            throw e;
        } catch (Exception ex) {
            logger.error("Error inesperado al guardar cuenta", ex);
            throw new ServiceException(messageService.getMessage("error.cuenta.get"), null);
        }
    	 
    }
    @Override
    public Movimiento getById(String id){
        var obj = jpaMovimientoRepository.findById(id).map(model -> mapper.toEntity(model));
        if (obj.isPresent())
            return obj.get();
        else
            return null;
    }
    @Override
    public void update(Movimiento movimiento){
        var movimientoModel = mapper.toModel(movimiento);
        //Save changes
        jpaMovimientoRepository.save(movimientoModel);
    }
    @Override
    public void delete(String id){
        jpaMovimientoRepository.deleteById(id);
    }
}
