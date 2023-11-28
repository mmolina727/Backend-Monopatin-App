package com.app.monopatin.entitys;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Monopatin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String estado;

    private int kilometros;

    private String latitud;

    private String longitud;

    private int tiempoEnUso;

    private int tiempoEnPausa;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="parada_id")
    private Parada _parada;


    public Monopatin(){

    }

    public Monopatin(int id, String estado, String latitud, String longitud, Parada _parada) {
        this.id = id;
        this.estado = estado;
        this.kilometros = 0;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempoEnUso = 0;
        this.tiempoEnPausa = 0;
        this._parada = _parada;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getKilometros() {
		return kilometros;
	}

	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public int getTiempoEnUso() {
		return tiempoEnUso;
	}

	public void setTiempoEnUso(int tiempoEnUso) {
		this.tiempoEnUso = tiempoEnUso;
	}

	public int getTiempoEnPausa() {
		return tiempoEnPausa;
	}

	public void setTiempoEnPausa(int tiempoEnPausa) {
		this.tiempoEnPausa = tiempoEnPausa;
	}

	public Parada getParada() {
		return _parada;
	}

	public void setParada(Parada parada) {
		this._parada = parada;
	}


}
