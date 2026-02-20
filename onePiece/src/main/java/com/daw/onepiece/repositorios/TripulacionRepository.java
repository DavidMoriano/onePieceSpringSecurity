package com.daw.onepiece.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daw.onepiece.dtos.TripulacionDTO;
import com.daw.onepiece.entities.TripulacionEntity;

@Repository
public interface TripulacionRepository extends CrudRepository<TripulacionEntity, Integer> {

	@Query("""
			    SELECT new com.daw.onepiece.dtos.TripulacionDTO(
			        t.id,
			        t.nombre,
			        t.barco,
			        t.estaActiva,
			        CAST(COALESCE(COUNT(r.id), 0) AS int)
			    )
			    FROM TripulacionEntity t
			    LEFT JOIN ReclutamientoEntity r
			        ON r.tripulacion = t
			        AND r.esMiembroActual = 1
			    GROUP BY t.id, t.nombre, t.barco, t.estaActiva
			    ORDER BY t.nombre
			""")
	ArrayList<TripulacionDTO> findAllConCantidadReclutados();

	@Query("""
			    SELECT new com.daw.onepiece.dtos.TripulacionDTO(
			        t.id,
			        t.nombre,
			        t.barco,
			        t.estaActiva,
			        CAST(COALESCE(COUNT(r.id), 0) AS int)
			    )
			    FROM TripulacionEntity t
			    LEFT JOIN ReclutamientoEntity r
			        ON r.tripulacion = t
			        AND r.esMiembroActual = 1
			    WHERE t.estaActiva = :activo
			      AND (:idPirata IS NULL OR EXISTS (
			          SELECT 1 FROM ReclutamientoEntity rec
			          WHERE rec.tripulacion = t AND rec.pirata.id = :idPirata
			      ))
			      AND (:pirataNombre IS NULL OR EXISTS (
			          SELECT 1 FROM ReclutamientoEntity rec2
			          WHERE rec2.tripulacion = t
			          AND LOWER(rec2.pirata.nombre) LIKE LOWER(CONCAT('%', :pirataNombre, '%'))
			      ))
			      AND (:barco IS NULL OR LOWER(t.barco) LIKE LOWER(CONCAT('%', :barco, '%')))
			    GROUP BY t.id, t.nombre, t.barco, t.estaActiva
			    ORDER BY t.nombre
			""")
	ArrayList<TripulacionDTO> buscarTripulacionesFiltradas(@Param("idPirata") Integer idPirata,
			@Param("pirataNombre") String pirataNombre, @Param("barco") String barco, @Param("activo") Integer activo);
}