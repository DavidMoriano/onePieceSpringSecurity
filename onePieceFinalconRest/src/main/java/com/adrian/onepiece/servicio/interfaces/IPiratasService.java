package com.adrian.onepiece.servicio.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import com.adrian.onepiece.dtos.PirataDTO;

public interface IPiratasService {

    Integer insertarPirata(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento, Integer activo,
            Integer idIsla);

    ArrayList<PirataDTO> obtenerPiratasPorFiltros(Integer id, String nombre, String frutaDiablo, Integer activo);

    Integer actualizarPirata(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento, Integer activo,
            Integer idIsla);

    Integer borrarPirata(Integer id);
    
    public ArrayList<PirataDTO> obtenerTodosPiratas();
}
