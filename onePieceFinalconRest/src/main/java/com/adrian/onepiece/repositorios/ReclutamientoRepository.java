package com.adrian.onepiece.repositorios;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.onepiece.entities.ReclutamientoEntity;

import jakarta.transaction.Transactional;

public interface ReclutamientoRepository extends CrudRepository<ReclutamientoEntity, Integer> {

        // Desactivar todos los reclutamientos activos de un pirata
        @Modifying
        @Transactional
        @Query("UPDATE com.adrian.onepiece.entities.ReclutamientoEntity r "
                        + "SET r.esMiembroActual = 0 "
                        + "WHERE r.pirata.id = :idPirata "
                        + "AND r.esMiembroActual = 1")
        void desactivarReclutamientosDelPirata(@Param("idPirata") Integer idPirata);

        // Eliminar miembro de tripulación (marcar como no actual)
        @Modifying
        @Transactional
        @Query("UPDATE com.adrian.onepiece.entities.ReclutamientoEntity r "
                        + "SET r.esMiembroActual = 0 "
                        + "WHERE r.pirata.id = :idPirata "
                        + "AND r.tripulacion.id = :idTripulacion")
        void eliminarMiembroDeTripulacion(@Param("idPirata") Integer idPirata,
                        @Param("idTripulacion") Integer idTripulacion);

        // Buscar reclutamiento activo de un pirata (para capturas)
        @Query("SELECT r FROM com.adrian.onepiece.entities.ReclutamientoEntity r "
                        + "WHERE r.pirata.id = :idPirata "
                        + "AND r.esMiembroActual = 1")
        ReclutamientoEntity buscarReclutamientoActivoPorPirata(@Param("idPirata") Integer idPirata);
}
