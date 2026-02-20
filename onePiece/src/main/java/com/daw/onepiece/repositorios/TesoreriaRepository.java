package com.daw.onepiece.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.daw.onepiece.dtos.TesoreriaDTO;
import com.daw.onepiece.entities.TesoreriaEntity;



public interface TesoreriaRepository extends CrudRepository<TesoreriaEntity, Integer> {

    @Query("SELECT new com.daw.onepiece.dtos.TesoreriaDTO(t.id, t.tipoOperacion, t.cantidad, t.fechaOperacion) "
            + "FROM com.daw.onepiece.entities.TesoreriaEntity t "
            + "ORDER BY t.fechaOperacion DESC")
    ArrayList<TesoreriaDTO> listarTodasLasOperaciones();
}
