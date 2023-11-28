package MonopatinApp.Dto;

import java.util.ArrayList;
import java.util.List;

import MonopatinApp.entities.Cuenta;

public class DtoUsuario {
	
	private Integer idUsuario;
	private String nombre;
	private String apellido;
	private String mail;
	private int numTelefono;
	private List<Cuenta>cuentas;
	
	public DtoUsuario(Integer idUsuario, String nombre, String apellido, String mail, int numTelefono, List<Cuenta>cuentas) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.numTelefono = numTelefono;
		this.cuentas=new ArrayList<>();
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	@Override
	public String toString() {
		return "DtoUsuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail
				+ ", numTelefono=" + numTelefono + "]";
	}
	
}
