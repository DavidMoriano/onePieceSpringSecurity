package com.adrian.onepiece.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.adrian.onepiece.dtos.TesoreriaDTO;
import com.adrian.onepiece.entities.TesoreriaEntity;

public interface TesoreriaRepository extends CrudRepository<TesoreriaEntity, Integer> {

    // Listar todas las operaciones de tesorería ordenadas por fecha descendente
    @Query("SELECT new com.adrian.onepiece.dtos.TesoreriaDTO(t.id, t.tipoOperacion, t.cantidad, t.fechaOperacion) "
            + "FROM com.adrian.onepiece.entities.TesoreriaEntity t "
            + "ORDER BY t.fechaOperacion DESC")
    ArrayList<TesoreriaDTO> listarTodasLasOperaciones();
}
