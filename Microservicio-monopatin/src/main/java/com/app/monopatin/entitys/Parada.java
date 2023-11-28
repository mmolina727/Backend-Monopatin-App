package com.app.monopatin.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String latitud;

    private String longitud;
    
    @JsonIgnore
    @OneToMany(mappedBy = "_parada")
    private List<Monopatin> monopatines;

    public Parada() {
        this.monopatines = new ArrayList<>();
    }

    public Parada(String latitud, String longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.monopatines = new ArrayList<>();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Monopatin> getMonopatines() {
		return monopatines;
	}

	public void setMonopatines(List<Monopatin> monopatines) {
		this.monopatines = monopatines;
	}
    
    


}
