package com.adrian.onepiece.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.onepiece.dtos.PirataDTO;
import com.adrian.onepiece.dtos.TripulacionDTO;
import com.adrian.onepiece.entities.TripulacionEntity;

public interface TripulacionRepository extends CrudRepository<TripulacionEntity, Integer> {

    // Listar todas las tripulaciones con el número de miembros
    @Query("SELECT new com.adrian.onepiece.dtos.TripulacionDTO(t.id, t.nombre, t.barco, t.estaActiva, COUNT(r.id)) "
            + "FROM com.adrian.onepiece.entities.TripulacionEntity t "
            + "LEFT JOIN com.adrian.onepiece.entities.ReclutamientoEntity r ON r.tripulacion = t AND r.esMiembroActual = 1 "
            + "WHERE (:nombre IS NULL OR t.nombre LIKE CONCAT('%', :nombre, '%')) "
            + "AND (:barco IS NULL OR t.barco LIKE CONCAT('%', :barco, '%'))"
            + "AND t.estaActiva = :estaActiva "
            + "GROUP BY t.id, t.nombre, t.barco, t.estaActiva")
    ArrayList<TripulacionDTO> listarTripulacionesConMiembros(@Param("nombre") String nombre,@Param("barco") String barco,
            @Param("estaActiva") Integer estaActiva);

    // Obtener miembros de una tripulación específica
    @Query("SELECT new com.adrian.onepiece.dtos.PirataDTO(p.id, p.nombre, r.rol, p.frutaDiablo) "
            + "FROM com.adrian.onepiece.entities.ReclutamientoEntity r "
            + "JOIN r.pirata p "
            + "WHERE r.tripulacion.id = :idTripulacion "
            + "AND r.esMiembroActual = 1")
    ArrayList<PirataDTO> obtenerMiembrosDeTripulacion(@Param("idTripulacion") Integer idTripulacion);

    // Buscar tripulaciones para actualizar/borrar
    @Query("SELECT new com.adrian.onepiece.dtos.TripulacionDTO(t.id, t.nombre, t.barco, t.estaActiva) "
            + "FROM com.adrian.onepiece.entities.TripulacionEntity t "
            + "WHERE (:id IS NULL OR CAST(t.id AS string) LIKE CONCAT('%', :id, '%')) "
            + "AND t.nombre LIKE CONCAT('%', :nombre, '%') "
            + "AND t.estaActiva = :estaActiva")
    ArrayList<TripulacionDTO> buscarTripulacionesPorFiltros(@Param("id") Integer id, @Param("nombre") String nombre,
            @Param("estaActiva") Integer estaActiva);
}
