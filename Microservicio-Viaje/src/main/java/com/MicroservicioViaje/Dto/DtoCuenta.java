package com.MicroservicioViaje.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class DtoCuenta {
	@JsonProperty("idCuenta")
	private Integer idCuenta;
	@JsonProperty("saldo")
	private double saldo;
	@JsonProperty("fechaAlta")
	private LocalDate fechaAlta;
	
	public DtoCuenta(Integer idCuenta, double saldo, LocalDate fechaAlta) {
		super();
		this.idCuenta = idCuenta;
		this.saldo = saldo;
		this.fechaAlta = fechaAlta;
	}

	public DtoCuenta() {
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "DtoCuenta [idCuenta=" + idCuenta + ", saldo=" + saldo + ", fechaAlta=" + fechaAlta + "]";
	}

}
