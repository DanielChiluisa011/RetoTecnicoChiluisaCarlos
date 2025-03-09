package com.retotecnico.proyecto.infrastructure.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

import jakarta.persistence.*;

@Accessors(chain = true)
@Entity
@Table(name = "CUENTA")
@Getter
@Setter
public class CuentaModel {
	@Id
    private String id;
    @Column(name = "numero")
    private String numero;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "saldoInicial")
    private String saldoInicial;

    @Column(name = "estado")
    private Boolean estado; 
    
    @Column(name = "clienteId")
    private String clienteId;
    
    @PrePersist
    public void prePersist(){
        this.id = UUID.randomUUID().toString();
    }

}
