package com.Microservicios.Mantenimiento.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DtoMonopatin {

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

    public DtoMonopatin(Integer id, String estado, int kilometros, String latitud, String longitud, int tiempoEnUso, int tiempoEnPausa) {
        this.id = id;
        this.estado = estado;
        this.kilometros = kilometros;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempoEnUso = tiempoEnUso;
        this.tiempoEnPausa = tiempoEnPausa;
    }

    public DtoMonopatin() {
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public void setTiempoEnUso(int tiempoEnUso) {
		this.tiempoEnUso = tiempoEnUso;
	}

	public void setTiempoEnPausa(int tiempoEnPausa) {
		this.tiempoEnPausa = tiempoEnPausa;
	}
    
    

}
