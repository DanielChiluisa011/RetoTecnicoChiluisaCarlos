package com.retotecnico.proyecto.infrastructure.repositories;

import com.retotecnico.proyecto.domain.entities.*;
import com.retotecnico.proyecto.domain.interfaces.IClienteRepository;
import com.retotecnico.proyecto.exceptions.MessageService;
import com.retotecnico.proyecto.exceptions.ServiceException;
import com.retotecnico.proyecto.infrastructure.mappers.ClienteMapper;
import com.retotecnico.proyecto.infrastructure.mappers.PersonaMapper;
import com.retotecnico.proyecto.infrastructure.model.ClienteModel;
import com.retotecnico.proyecto.infrastructure.model.PersonaModel;
import com.retotecnico.proyecto.infrastructure.repositories.jpa.JpaClienteRepository;
import com.retotecnico.proyecto.infrastructure.repositories.jpa.JpaPersonaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository

public class ClienteRepository implements IClienteRepository {
    public static final String SENDING_COMMAND = "------ Sending command: {} {}";
    private static final Logger logger = LoggerFactory.getLogger(ClienteRepository.class);
    @Autowired
    private JpaClienteRepository jpaClienteRepository;
    @Autowired
    private JpaPersonaRepository jpaPersonaRepository;
    @Autowired
    private ClienteMapper mapper;
    @Autowired
    private PersonaMapper personaMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public String save(Cliente cliente){
    	try {
    		if (personaRepository.getByIdentificacion(cliente.getIdentificacion()) != null) {
                throw new ServiceException(messageService.getMessage("error.cliente.ya_existe"), null);
            }
    		Persona persona = new Persona();
    		persona.setNombre(cliente.getNombre());
    		persona.setDireccion(cliente.getDireccion());
    		persona.setEdad(cliente.getEdad());
    		persona.setGenero(cliente.getGenero());
    		persona.setIdentificacion(cliente.getIdentificacion());
    		persona.setTelefono(cliente.getTelefono());	
    		ClienteModel clienteModel = mapper.toModel(cliente);	
    		clienteModel.setPERSONA(personaMapper.toModel(persona));
    		var clienteId = this.jpaClienteRepository.save(clienteModel).getId();
    		return clienteId;
    	}catch (ServiceException e) {
            logger.warn("Error al crear cliente: {}", e.getMessage());
            throw e;
        } catch (Exception ex) {
            logger.error("Error inesperado al crear cliente", ex);
            throw new ServiceException(messageService.getMessage("error.cliente.save"), null);
        }
    }
    @Override
    public Cliente getById(String id){
    	try {
    		
    		ClienteModel cliente = jpaClienteRepository.findById(id).orElse(null);
    		if (cliente == null) {
                throw new ServiceException(messageService.getMessage("error.cliente.no_existe"), null);
            }
        	PersonaModel persona = jpaPersonaRepository.findById(cliente.getPERSONA().getId()).orElse(null);
            
        	Cliente clienteEntity = mapper.toEntity(cliente);
        	clienteEntity.setNombre(persona.getNombre());
        	clienteEntity.setDireccion(persona.getDireccion());
        	clienteEntity.setGenero(persona.getGenero());
        	clienteEntity.setIdentificacion(persona.getIdentificacion());
        	clienteEntity.setEdad(persona.getEdad());
        	clienteEntity.setTelefono(persona.getTelefono());
        	clienteEntity.setPersonaId(persona.getId());
        	return clienteEntity;
    	}catch (ServiceException e) {
            logger.warn("Error al buscar cliente: {}", e.getMessage());
            throw e;
        } catch (Exception ex) {
            logger.error("Error inesperado al buscar cliente", ex);
            throw new ServiceException(messageService.getMessage("error.cliente.get"), null);
        }
    	
    }
    @Override
    public void update(Cliente cliente){
        var clienteModel = mapper.toModel(cliente);
        jpaClienteRepository.save(clienteModel);
    }
    @Override
    public void delete(String id){
    	try {
    		logger.info("delete {}", id);
            ClienteModel cliente = jpaClienteRepository.findById(id).orElse(null);
            if (cliente == null) {
                throw new ServiceException(messageService.getMessage("error.cliente.no_existe"), null);
            }
            String personaId = jpaPersonaRepository.getById(cliente.getPERSONA().getId()).getId();
            jpaClienteRepository.deleteById(id);
            jpaPersonaRepository.deleteById(personaId);
    	}catch (ServiceException e) {
            logger.warn("Error al borrar cliente: {}", e.getMessage());
            throw e;
        } catch (Exception ex) {
            logger.error("Error inesperado al borrar cliente", ex);
            throw new ServiceException(messageService.getMessage("error.cliente.delete"), null);
        }
        
    }

}
