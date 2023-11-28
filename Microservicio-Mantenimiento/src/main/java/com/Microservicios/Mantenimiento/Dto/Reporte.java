package com.Microservicios.Mantenimiento.Dto;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class Reporte {
    private Integer id_monopatin;
    private int km_recorrido;
    private double tiempo_usado;


    public Reporte() {
    }



    public Reporte(Integer id_monopatin, int km_recorrido, double tiempo_usado) {
        this.id_monopatin = id_monopatin;
        this.km_recorrido = km_recorrido;
        this.tiempo_usado = tiempo_usado;
    }
}
