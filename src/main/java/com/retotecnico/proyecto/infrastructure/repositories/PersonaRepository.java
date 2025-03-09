package com.retotecnico.proyecto.infrastructure.repositories;

import com.retotecnico.proyecto.domain.entities.Persona;
import com.retotecnico.proyecto.domain.interfaces.IPersonaRepository;
import com.retotecnico.proyecto.infrastructure.mappers.PersonaMapper;
import com.retotecnico.proyecto.infrastructure.repositories.jpa.JpaPersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository

public class PersonaRepository implements IPersonaRepository {
    public static final String SENDING_COMMAND = "------ Sending command: {} {}";
    private static final Logger logger = LoggerFactory.getLogger(PersonaRepository.class);
    @Autowired
    private JpaPersonaRepository jpaPersonaRepository;
    @Autowired
    private PersonaMapper mapper;

    @Override
    public String save(Persona persona){
    	return this.jpaPersonaRepository.save(mapper.toModel(persona)).getId();
    }
    @Override
    public Persona getById(String id){
        var obj = jpaPersonaRepository.findById(id).map(model -> mapper.toEntity(model));
        if (obj.isPresent())
            return obj.get();
        else
            return null;
    }
    @Override
    public void update(Persona persona){
        var personaModel = mapper.toModel(persona);
        jpaPersonaRepository.save(personaModel);
    }
    @Override
    public void delete(String id){
        logger.info("delete {}", id);
        jpaPersonaRepository.deleteById(id);
    }
	@Override
	public Persona getByIdentificacion(String identificacion) {
		var obj = jpaPersonaRepository.findByIdentificacion(identificacion).map(model -> mapper.toEntity(model));
        if (obj.isPresent())
            return obj.get();
        else
            return null;
	}

}
