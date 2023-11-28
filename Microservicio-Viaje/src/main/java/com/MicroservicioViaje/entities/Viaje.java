package com.MicroservicioViaje.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Viaje implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_viaje;
    private int id_cuenta,id_monopatin;
    private Date fecha_inicio,fecha_fin;
    private double km_recorridos,precio,tarifa_extra;

    public Viaje() {
    }

    public Viaje(int id_cuenta, int id_monopatin, Date fecha_inicio,  double precio, double tarifa_extra) {
        this.id_cuenta = id_cuenta;
        this.id_monopatin = id_monopatin;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = null;
        this.km_recorridos = 0;
        this.precio = precio;
        this.tarifa_extra = tarifa_extra;
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

    @Override
    public String toString() {
        return "Viaje{" +
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


}
