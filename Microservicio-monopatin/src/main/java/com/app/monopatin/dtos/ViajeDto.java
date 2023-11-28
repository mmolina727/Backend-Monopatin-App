package com.app.monopatin.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ViajeDto  {
    @JsonProperty("id_viaje")
    private int id_viaje;
    @JsonProperty("id_cuenta")
    private int id_cuenta;
    @JsonProperty("id_monopatin")
    private int id_monopatin;
    @JsonProperty("fecha_inicio")
    private Date fecha_inicio;
    @JsonProperty("fecha_fin")
    private Date fecha_fin;
    @JsonProperty("km_recorridos")
    private double km_recorridos;
    @JsonProperty("precio")
    private double precio;
    @JsonProperty("tarifa_extra")
    private double tarifa_extra;

    public ViajeDto() {
    }

    public ViajeDto(int id_viaje, int id_cuenta, int id_monopatin, Date fecha_inicio, Date fecha_fin, double km_recorridos, double precio, double tarifa_extra) {
        this.id_viaje = id_viaje;
        this.id_cuenta = id_cuenta;
        this.id_monopatin = id_monopatin;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.km_recorridos = km_recorridos;
        this.precio = precio;
        this.tarifa_extra = tarifa_extra;
    }

    @Override
    public String toString() {
        return "ViajeDto{" +
                "id_viaje=" + id_viaje +
                ", id_cuenta=" + id_cuenta +
                ", id_monopatin=" + id_monopatin +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", km_recorridos=" + km_recorridos +
                ", precio=" + precio +
                ", tarifa_extra=" + tarifa_extra +
                '}';
    }

	public int getId_viaje() {
		return id_viaje;
	}

	public int getId_cuenta() {
		return id_cuenta;
	}

	public int getId_monopatin() {
		return id_monopatin;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public double getKm_recorridos() {
		return km_recorridos;
	}

	public double getPrecio() {
		return precio;
	}

	public double getTarifa_extra() {
		return tarifa_extra;
	}
    
    
}
