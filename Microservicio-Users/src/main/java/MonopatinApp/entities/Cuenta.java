package MonopatinApp.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCuenta;
	
	private double saldo;
	private LocalDate fechaAlta;
	@ManyToMany(mappedBy = "cuentas")
	private List<Usuario>usuarios;
	
	public Cuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cuenta(double saldo, LocalDate fechaAlta) {
		super();
		this.saldo = saldo;
		this.fechaAlta = fechaAlta;
		this.usuarios = new ArrayList<>();
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	@Override
	public String toString() {
		return "Cuenta [idCuenta=" + idCuenta + ", saldo=" + saldo + ", fechaAlta=" + fechaAlta + ", usuarios="
				+ usuarios + "]";
	}

}
