package com.retotecnico.proyecto.infrastructure.repositories;

import com.retotecnico.proyecto.domain.entities.Cuenta;
import com.retotecnico.proyecto.domain.entities.Movimiento;
import com.retotecnico.proyecto.domain.interfaces.ICuentaRepository;
import com.retotecnico.proyecto.exceptions.MessageService;
import com.retotecnico.proyecto.exceptions.ServiceException;
import com.retotecnico.proyecto.infrastructure.mappers.CuentaMapper;
import com.retotecnico.proyecto.infrastructure.mappers.MovimientoMapper;
import com.retotecnico.proyecto.infrastructure.model.MovimientoModel;
import com.retotecnico.proyecto.infrastructure.repositories.jpa.JpaCuentaRepository;
import com.retotecnico.proyecto.infrastructure.repositories.jpa.JpaMovimientoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class CuentaRepository implements ICuentaRepository {
    public static final String SENDING_COMMAND = "------ Sending command: {} {}";
    private static final Logger logger = LoggerFactory.getLogger(CuentaRepository.class);

    @Autowired
    private JpaCuentaRepository jpaCuentaRepository;
    @Autowired
    private JpaMovimientoRepository jpaMovimientoRepository;

    @Autowired
    private CuentaMapper mapper;
    @Autowired
    private MovimientoMapper movimientoMapper;

    @Autowired
    private MessageService messageService;
    @Override
    public String save(Cuenta cuenta){
    	try {
    		String cuentaId = this.jpaCuentaRepository.save(mapper.toModel(cuenta)).getId(); 
    		Movimiento movimiento = new Movimiento();
    		movimiento.setCuentaNumero(cuenta.getNumero());
    		movimiento.setSaldo(cuenta.getSaldoInicial());
    		movimiento.setTipo(true);
    		movimiento.setValor(cuenta.getSaldoInicial());
    		MovimientoModel movimientoModel = movimientoMapper.toModel(movimiento);
    		movimientoModel.setCuentaId(cuentaId);
    		jpaMovimientoRepository.save(movimientoModel);
    		return cuentaId;
    	}catch (ServiceException e) {
            logger.warn("Error al guardar cuenta: {}", e.getMessage());
            throw e;
        } catch (Exception ex) {
            logger.error("Error inesperado al guardar cuenta", ex);
            throw new ServiceException(messageService.getMessage("error.cuenta.get"), null);
        }
        
    }
    @Override
    public Cuenta getById(String id){
    	try {
    		
            var obj = jpaCuentaRepository.findById(id).map(model -> mapper.toEntity(model));
            if (!obj.isPresent())
            	throw new ServiceException(messageService.getMessage("error.cuenta.get"), null);
            return obj.get();
    	}
    	catch (ServiceException e) {
            logger.warn("Error al buscar cuenta: {}", e.getMessage());
            throw e;
        } catch (Exception ex) {
            logger.error("Error inesperado al buscar cuenta", ex);
            throw new ServiceException(messageService.getMessage("error.cuenta.get"), null);
        }
    }

    @Override
    public Cuenta getByNumero(String numero){
    	try {
    		var obj = jpaCuentaRepository.findByNumero(numero).map(model -> mapper.toEntity(model));
            if (!obj.isPresent())
            	throw new ServiceException(messageService.getMessage("error.cuenta.get"), null);
            return obj.get();
    	}catch (ServiceException e) {
            logger.warn("Error al buscar cuenta: {}", e.getMessage());
            throw e;
        } catch (Exception ex) {
            logger.error("Error inesperado al buscar cuenta", ex);
            throw new ServiceException(messageService.getMessage("error.cuenta.get"), null);
        }
    	
        
    }
    @Override
    public void update(Cuenta cuenta){
        var cuentaModel = mapper.toModel(cuenta);
        //Save changes
        jpaCuentaRepository.save(cuentaModel);
    }
    @Override
    public void delete(String id){
    	try {
    		logger.info("delete {}", id);
            jpaCuentaRepository.deleteById(id);
    	}catch (ServiceException e) {
            logger.warn("Error al buscar cuenta: {}", e.getMessage());
            throw e;
        } catch (Exception ex) {
            logger.error("Error inesperado al buscar cuenta", ex);
            throw new ServiceException(messageService.getMessage("error.cuenta.get"), null);
        }
    	
    }
}
