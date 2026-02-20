package com.daw.onepiece.repositorios;

import com.daw.onepiece.dtos.PirataDTO;
import com.daw.onepiece.entities.PirataEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface PirataRepository extends CrudRepository<PirataEntity, Integer> {

	@Query("""
			    SELECT new com.daw.onepiece.dtos.PirataDTO(
			        p.id,
			        p.nombre,
			        p.frutaDelDiablo,
			        t.nombre,
			        p.fechaNacimiento,
			        i.nombre,
			        i.id,
			        p.estaActivo
			    )
			    FROM PirataEntity p
			    LEFT JOIN p.isla i
			    LEFT JOIN p.reclutamiento r
			    LEFT JOIN r.tripulacion t
			""")
	ArrayList<PirataDTO> listarTodosPiratasSinFiltros();

	@Query("""
			    SELECT new com.daw.onepiece.dtos.PirataDTO(
			        p.id,
			        p.nombre,
			        p.frutaDelDiablo,
			        t.nombre,
			        p.fechaNacimiento,
			        i.nombre,
			        i.id,
			        p.estaActivo
			    )
			    FROM PirataEntity p
			    LEFT JOIN p.isla i
			    LEFT JOIN p.reclutamiento r
			    LEFT JOIN r.tripulacion t
			    WHERE p.estaActivo = :activo
			      AND (:idPirata   IS NULL OR p.id = :idPirata)
			      AND (:nombrePirata   IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombrePirata, '%')))
			      AND (:frutaDiablo IS NULL OR LOWER(p.frutaDelDiablo) LIKE LOWER(CONCAT('%', :frutaDiablo, '%')))
			""")
	ArrayList<PirataDTO> listarPiratasConFiltros(@Param("idPirata") Integer id,
			@Param("nombrePirata") String nombrePirata, @Param("frutaDiablo") String frutaDiablo,
			@Param("activo") int activoInt);

	@Query("""
			    SELECT new com.daw.onepiece.dtos.PirataDTO(
			        p.id,
			        p.nombre,
			        p.frutaDelDiablo,
			        null,
			        p.fechaNacimiento,
			        i.nombre,
			        i.id,
			        p.estaActivo
			    )
			    FROM PirataEntity p
			    LEFT JOIN p.isla i
			    WHERE NOT EXISTS (
			        SELECT 1
			        FROM ReclutamientoEntity r
			        WHERE r.pirata = p
			    )
			""")
	ArrayList<PirataDTO> listarPiratasSinReclutamiento();

	@Query("""
			    SELECT new com.daw.onepiece.dtos.PirataDTO(
			        p.id,
			        p.nombre,
			        p.frutaDelDiablo,
			        null,
			        p.fechaNacimiento,
			        i.nombre,
			        i.id,
			        p.estaActivo
			    )
			    FROM PirataEntity p
			    LEFT JOIN p.isla i
			    WHERE NOT EXISTS (
			        SELECT 1
			        FROM ReclutamientoEntity r
			        WHERE r.pirata = p
			    )
			    AND (:activo IS NULL OR p.estaActivo = :activo)
			""")
	ArrayList<PirataDTO> listarPiratasSinReclutamientoFiltradoPorActivo(@Param("activo") Integer activo);

}