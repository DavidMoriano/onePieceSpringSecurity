package com.adrian.onepiece.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.onepiece.dtos.RecompensaDTO;
import com.adrian.onepiece.entities.RecompensaEntity;

import jakarta.transaction.Transactional;

public interface RecompensaRepository extends CrudRepository<RecompensaEntity, Integer> {

        // Listar recompensas con filtros
        @Query("SELECT new com.adrian.onepiece.dtos.RecompensaDTO(r.id, p.id, p.nombre, t.nombre, r.cantidad, r.estaVigente) "
                        + "FROM com.adrian.onepiece.entities.RecompensaEntity r "
                        + "JOIN r.pirata p "
                        + "LEFT JOIN com.adrian.onepiece.entities.ReclutamientoEntity rec ON rec.pirata = p AND rec.esMiembroActual = 1 "
                        + "LEFT JOIN rec.tripulacion t "
                        + "WHERE (:nombrePirata IS NULL OR p.nombre LIKE CONCAT('%', :nombrePirata, '%')) "
                        + "AND (:idTripulacion IS NULL OR t.id = :idTripulacion) "
                        + "AND (:cantidad IS NULL OR r.cantidad >= :cantidad) "
                        + "AND r.estaVigente = :estaVigente")
        ArrayList<RecompensaDTO> listarRecompensasConFiltros(@Param("nombrePirata") String nombrePirata,
                        @Param("idTripulacion") Integer idTripulacion, @Param("cantidad") Long cantidad,
                        @Param("estaVigente") Integer estaVigente);

        // Buscar recompensas para actualizar/borrar
        @Query("SELECT new com.adrian.onepiece.dtos.RecompensaDTO(r.id, p.id, p.nombre, r.cantidad, r.estaVigente) "
                        + "FROM com.adrian.onepiece.entities.RecompensaEntity r "
                        + "JOIN r.pirata p "
                        + "WHERE (:id IS NULL OR CAST(r.id AS string) LIKE CONCAT('%', :id, '%')) "
                        + "AND p.nombre LIKE CONCAT('%', :nombrePirata, '%') "
                        + "AND r.estaVigente = :estaVigente")
        ArrayList<RecompensaDTO> buscarRecompensasPorFiltros(@Param("id") Integer id,
                        @Param("nombrePirata") String nombrePirata, @Param("estaVigente") Integer estaVigente);

        // Desactivar recompensas anteriores de un pirata
        @Modifying
        @Transactional
        @Query("UPDATE com.adrian.onepiece.entities.RecompensaEntity r "
                        + "SET r.estaVigente = 0 "
                        + "WHERE r.pirata.id = :idPirata "
                        + "AND r.estaVigente = 1")
        void desactivarRecompensasDelPirata(@Param("idPirata") Integer idPirata);

        // Buscar recompensa vigente de un pirata (para capturas)
        @Query("SELECT r FROM com.adrian.onepiece.entities.RecompensaEntity r "
                        + "WHERE r.pirata.id = :idPirata "
                        + "AND r.estaVigente = 1")
        RecompensaEntity buscarRecompensaVigentePorPirata(@Param("idPirata") Integer idPirata);
}
