package com.daw.onepiece.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daw.onepiece.dtos.RecompensaDTO;
import com.daw.onepiece.entities.RecompensaEntity;

@Repository
public interface RecompensaRepository extends CrudRepository<RecompensaEntity, Integer> {

	@Query("""
			    SELECT new com.daw.onepiece.dtos.RecompensaDTO(
			        r.id,
			        p.id,
			        p.nombre,
			        t.nombre,
			        r.cantidad,
			        r.estaVigente
			    )
			    FROM RecompensaEntity r
			    JOIN r.pirata p
			    LEFT JOIN ReclutamientoEntity rec ON rec.pirata = p AND rec.esMiembroActual = 1
			    LEFT JOIN rec.tripulacion t
			    ORDER BY r.cantidad DESC
			""")
	ArrayList<RecompensaDTO> findAllRecompensas();

	@Query("""
			    SELECT new com.daw.onepiece.dtos.RecompensaDTO(
			        r.id,
			        p.id,
			        p.nombre,
			        t.nombre,
			        r.cantidad,
			        r.estaVigente
			    )
			    FROM RecompensaEntity r
			    JOIN r.pirata p
			    LEFT JOIN ReclutamientoEntity rec ON rec.pirata = p AND rec.esMiembroActual = 1
			    LEFT JOIN rec.tripulacion t
			    WHERE (:nombrePirata IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombrePirata, '%')))
			    AND (:idTripulacion IS NULL OR t.id = :idTripulacion)
			    AND (:cantidad IS NULL OR r.cantidad >= :cantidad)
			    AND (:vigente IS NULL OR r.estaVigente = :vigente)
			    ORDER BY r.cantidad DESC
			""")
	ArrayList<RecompensaDTO> buscarRecompensasFiltradas(@Param("nombrePirata") String nombrePirata,
			@Param("idTripulacion") Integer idTripulacion, @Param("cantidad") Long cantidad,
			@Param("vigente") Integer vigente);

	@Modifying
	@Query("""
			    UPDATE RecompensaEntity r
			    SET r.estaVigente = 0
			    WHERE r.pirata.id = :idPirata AND r.estaVigente = 1
			""")
	int desactivarRecompensasVigentesDePirata(@Param("idPirata") Integer idPirata);

	@Modifying
	@Query("""
			    UPDATE RecompensaEntity r
			    SET r.cantidad = :cantidad, r.estaVigente = :vigente
			    WHERE r.id = :id
			""")
	int actualizarRecompensa(@Param("id") Integer id, @Param("cantidad") Long cantidad,
			@Param("vigente") int vigente);

	@Modifying
	@Query("""
			    UPDATE RecompensaEntity r
			    SET r.estaVigente = 0
			    WHERE r.id = :id
			""")
	int marcarNoVigente(@Param("id") Integer id);
}