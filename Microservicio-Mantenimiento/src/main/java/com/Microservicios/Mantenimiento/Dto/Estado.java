package com.Microservicios.Mantenimiento.Dto;

import lombok.Data;

@Data
public class Estado {
    private String estado;

    public Estado(String estado) {
        this.estado = estado;
    }

    public Estado() {
    }
}
