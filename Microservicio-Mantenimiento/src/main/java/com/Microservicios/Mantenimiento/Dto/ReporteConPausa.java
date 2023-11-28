package com.Microservicios.Mantenimiento.Dto;

import lombok.Data;

@Data
public class ReporteConPausa extends Reporte{
    private double tiempo_en_pausa;
    public ReporteConPausa(Integer id_monopatin, int km_recorrido, double tiempo_usado, double tiempo_en_pausa) {
        super(id_monopatin,km_recorrido,tiempo_usado);
        this.tiempo_en_pausa = tiempo_en_pausa;
    }


}
