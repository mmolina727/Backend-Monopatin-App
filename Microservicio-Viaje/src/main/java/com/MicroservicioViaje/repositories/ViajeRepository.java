package com.MicroservicioViaje.repositories;

import com.MicroservicioViaje.entities.Viaje;

import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {

	
	@Query("SELECT SUM((v.precio * v.km_recorridos)+ v.tarifa_extra) FROM Viaje v "+
			"WHERE v.fecha_fin BETWEEN :inicio AND :fin")
	double getRecaudacion(@Param("inicio") Date inicio,@Param("fin")Date fin);
	@Transactional
	@Modifying
	@Query("UPDATE Viaje v SET v.precio= :precio WHERE v.fecha_inicio>=:fecha")
	void updatePrecio(@Param("precio") double precio, @Param("fecha") Date fecha);
	
	@Query("SELECT v.id_monopatin FROM Viaje v " +
			"WHERE FUNCTION('YEAR', v.fecha_inicio) = :year " +
			"GROUP BY v.id_monopatin " +
			"HAVING COUNT(*) > :cant")
	List<Integer> getAllIn(@Param("year") int year, @Param("cant") int cant);
}
