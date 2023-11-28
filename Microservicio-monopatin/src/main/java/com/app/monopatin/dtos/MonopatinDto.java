package com.app.monopatin.dtos;


import com.app.monopatin.entitys.Parada;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class MonopatinDto {

    private Integer id;

    private String estado;

    private int kilometros;

    private String latitud;

    private String longitud;

    private int tiempoEnUso;

    private int tiempoEnPausa;

    private int idParada;

    public MonopatinDto(Integer id, String estado, int kilometros, String latitud, String longitud, int tiempoEnUso, int tiempoEnPausa, int idParada) {
        this.id = id;
        this.estado = estado;
        this.kilometros = kilometros;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempoEnUso = tiempoEnUso;
        this.tiempoEnPausa = tiempoEnPausa;
        this.idParada = idParada;
    }

    public Integer getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public int getKilometros() {
        return kilometros;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public int getTiempoEnUso() {
        return tiempoEnUso;
    }

    public int getTiempoEnPausa() {
        return tiempoEnPausa;
    }

    public int getIdParada() {
        return idParada;
    }
}
