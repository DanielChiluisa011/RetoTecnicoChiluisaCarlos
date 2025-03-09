package com.retotecnico.proyecto.infrastructure.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.UUID;
import jakarta.persistence.*;

@Accessors(chain = true)

@Table(name = "CLIENTE")
@Getter
@Setter
@Entity
public class ClienteModel {

	@Id
    private String id;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado")
    private boolean estado;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "persona_id", referencedColumnName = "id", nullable = false)
    private PersonaModel PERSONA;
    @PrePersist
    public void prePersist(){
        this.id = UUID.randomUUID().toString();
    }
}