package com.retotecnico.proyecto.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "PERSONA")
public class PersonaModel {

    @Id
    private String id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "genero")
    private String genero;
    @Column(name = "edad")
    private int edad;
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @PrePersist
    public void prePersist(){
        this.id = UUID.randomUUID().toString();
    }

}