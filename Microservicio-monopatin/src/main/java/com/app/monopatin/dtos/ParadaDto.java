package com.app.monopatin.dtos;


import java.util.List;

public class ParadaDto {

    private int id;

    private String latitud;

    private String longitud;

    private int monopatines;


    public ParadaDto(int id, String latitud, String longitud, int monopatines) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.monopatines = monopatines;
    }

    public int getId() {
        return id;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }
}
