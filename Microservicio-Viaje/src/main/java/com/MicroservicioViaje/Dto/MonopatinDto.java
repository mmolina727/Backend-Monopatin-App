package com.MicroservicioViaje.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonopatinDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("kilometros")
    private int kilometros;

    @JsonProperty("latitud")
    private String latitud;

    @JsonProperty("longitud")
    private String longitud;

    @JsonProperty("tiempoEnUso")
    private int tiempoEnUso;

    @JsonProperty("tiempoEnPausa")
    private int tiempoEnPausa;

    public MonopatinDto(Integer id, String estado, int kilometros, String latitud, String longitud, int tiempoEnUso, int tiempoEnPausa) {
        this.id = id;
        this.estado = estado;
        this.kilometros = kilometros;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempoEnUso = tiempoEnUso;
        this.tiempoEnPausa = tiempoEnPausa;
    }

    public MonopatinDto() {
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
}
