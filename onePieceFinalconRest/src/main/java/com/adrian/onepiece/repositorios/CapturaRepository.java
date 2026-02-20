package com.adrian.onepiece.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.onepiece.dtos.CapturaDTO;
import com.adrian.onepiece.entities.CapturaEntity;

public interface CapturaRepository extends CrudRepository<CapturaEntity, Long> {

        // Listar capturas con filtros
        @Query("SELECT new com.adrian.onepiece.dtos.CapturaDTO(c.id, p.id, p.nombre, c.lugarCaptura, c.recompensaCobrada) "
                        + "FROM com.adrian.onepiece.entities.CapturaEntity c "
                        + "JOIN c.pirata p "
                        + "WHERE (:nombrePirata IS NULL OR p.nombre LIKE CONCAT('%', :nombrePirata, '%')) "
                        + "AND (:lugarCaptura IS NULL OR c.lugarCaptura LIKE CONCAT('%', :lugarCaptura, '%')) "
                        + "AND (:recompensaCobrada IS NULL OR c.recompensaCobrada >= :recompensaCobrada)")
        ArrayList<CapturaDTO> listarCapturasConFiltros(@Param("nombrePirata") String nombrePirata,
                        @Param("lugarCaptura") String lugarCaptura, @Param("recompensaCobrada") Long recompensaCobrada);
}
