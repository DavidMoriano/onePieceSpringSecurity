package com.daw.onepiece.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daw.onepiece.dtos.MiembroTripulacionDTO;
import com.daw.onepiece.dtos.PirataDTO;
import com.daw.onepiece.entities.ReclutamientoEntity;

@Repository
public interface ReclutamientoRepository extends CrudRepository<ReclutamientoEntity, Integer> {

	@Modifying
	@Query("""
			    UPDATE ReclutamientoEntity r
			    SET r.esMiembroActual = 0
			    WHERE r.pirata.id = :idPirata
			    AND r.esMiembroActual = 1
			""")
	int desactivarMiembroActual(@Param("idPirata") Integer idPirata);

	@Modifying
	@Query("""
			    UPDATE ReclutamientoEntity r
			    SET r.esMiembroActual = 0
			    WHERE r.pirata.id = :idPirata
			    AND r.tripulacion.id = :idTripulacion
			    AND r.esMiembroActual = 1
			""")
	int desactivarMiembroDeTripulacion(@Param("idPirata") Integer idPirata,
			@Param("idTripulacion") Integer idTripulacion);

	@Query("""
			    SELECT COUNT(r)
			    FROM ReclutamientoEntity r
			    WHERE r.tripulacion.id = :idTripulacion
			    AND r.esMiembroActual = 1
			""")
	int contarMiembrosActivos(@Param("idTripulacion") Integer idTripulacion);

	@Query("""
		    SELECT new com.daw.onepiece.dtos.MiembroTripulacionDTO(
		        p.id,
		        p.nombre,
		        p.frutaDelDiablo,
		        t.nombre,
		        p.fechaNacimiento,
		        i.nombre,
		        i.id,
		        p.estaActivo,
		        r.rol
		    )
		    FROM ReclutamientoEntity r
		    JOIN r.pirata p
		    LEFT JOIN p.isla i
		    LEFT JOIN r.tripulacion t
		    WHERE r.tripulacion.id = :idTripulacion
		    AND r.esMiembroActual = 1
		""")
		ArrayList<MiembroTripulacionDTO> obtenerPiratasActivosDeTripulacion(@Param("idTripulacion") Integer idTripulacion);

}