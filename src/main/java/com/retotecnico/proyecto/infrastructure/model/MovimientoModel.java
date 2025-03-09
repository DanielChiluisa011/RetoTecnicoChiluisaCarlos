package com.retotecnico.proyecto.infrastructure.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Accessors(chain = true)
@Entity
@Table(name = "MOVIMIENTO")
@Getter
@Setter
public class MovimientoModel {
    @Id
    private String  id;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "tipo")
    private Boolean tipo;
    @Column(name = "valor")
    private float valor;
    @Column(name = "saldo")
    private float saldo;
    @Column(name = "cuentaId")
    private String cuentaId;
    @PrePersist
    public void prePersist(){
        this.id = UUID.randomUUID().toString();
        this.fecha = new Date(System.currentTimeMillis());
    }
}
