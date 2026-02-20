package com.adrian.onepiece.dao.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import com.adrian.onepiece.dtos.PirataDTO;

public interface IPiratasDAO {

    int insertarPirata(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento, Integer activo,
            Integer idIsla);

    ArrayList<PirataDTO> obtenerPiratasPorFiltros(Integer id, String nombre, String frutaDiablo, Integer activo);

    int actualizarPirata(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento, Integer activo,
            Integer idIsla);

    int borrarPirata(Integer id);
    
    public ArrayList<PirataDTO> obtenerTodosPiratas(); 
}
