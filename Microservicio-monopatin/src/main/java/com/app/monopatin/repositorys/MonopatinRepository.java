package com.app.monopatin.repositorys;

import com.app.monopatin.entitys.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface MonopatinRepository extends JpaRepository<Monopatin, Integer> {
	


	 @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.estado='Disponible'")   
	 Integer getCantMonopatinDisponible();
	 
	 @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.estado='Mantenimiento'")
	 int getCantMonopatinMantenimiento();

	@Query("SELECT m FROM Monopatin m " +
			"WHERE ( CAST(m.latitud as DOUBLE ) BETWEEN :latitud - :distancia AND :latitud + :distancia) " +
			"AND ( CAST(m.longitud as DOUBLE ) BETWEEN :longitud - :distancia AND :longitud + :distancia)")
	List<Monopatin> getMonopatinesCercanosAMonopatin(@Param("latitud")double latitud,@Param("longitud")double longitud, @Param("distancia") double distancia);




	@Query(
			"SELECT m FROM Monopatin m WHERE m.id IN :listaID"
	)
	List<Monopatin> getAllInList(@Param("listaID") List<Integer> listaID);
}
