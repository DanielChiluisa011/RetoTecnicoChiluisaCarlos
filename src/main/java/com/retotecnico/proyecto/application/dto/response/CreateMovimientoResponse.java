package com.retotecnico.proyecto.application.dto.response;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMovimientoResponse {
    private String movimientoId;

    public CreateMovimientoResponse(String movimientoId){
        this.movimientoId = movimientoId;
    }
}
