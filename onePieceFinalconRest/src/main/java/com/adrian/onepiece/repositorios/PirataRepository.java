package com.adrian.onepiece.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.onepiece.dtos.PirataDTO;
import com.adrian.onepiece.entities.PirataEntity;

public interface PirataRepository extends CrudRepository<PirataEntity, Integer> {

    @Query("SELECT new com.adrian.onepiece.dtos.PirataDTO(p.id, p.nombre, p.frutaDiablo, p.fechaNacimiento, p.activo, p.isla.nombre, p.isla.id,  t.nombre) "
            + "FROM com.adrian.onepiece.entities.PirataEntity p "
    		+ "LEFT JOIN com.adrian.onepiece.entities.ReclutamientoEntity r ON r.pirata = p "
    		+ "LEFT JOIN com.adrian.onepiece.entities.TripulacionEntity t ON r.tripulacion = t "
            + "WHERE (:id IS NULL OR CAST(p.id AS string) LIKE CONCAT('%', :id, '%')) "
            + "AND p.nombre LIKE CONCAT('%', :nombre, '%') "
            + "AND (:frutaDiablo IS NULL OR p.frutaDiablo LIKE CONCAT('%', :frutaDiablo, '%')) "
            + "AND p.activo = :activo "
            + "AND r.esMiembroActual = 1")
    ArrayList<PirataDTO> buscarPiratasPorFiltros(@Param("id") Integer id,
            @Param("nombre") String nombre,
            @Param("frutaDiablo") String frutaDiablo,
            @Param("activo") Integer activo);
}
