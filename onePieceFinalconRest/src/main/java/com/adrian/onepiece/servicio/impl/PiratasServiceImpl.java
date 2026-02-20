package com.adrian.onepiece.servicio.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.onepiece.dao.interfaces.IPiratasDAO;
import com.adrian.onepiece.dtos.PirataDTO;
import com.adrian.onepiece.servicio.interfaces.IPiratasService;

@Service
public class PiratasServiceImpl implements IPiratasService {

    @Autowired
    private IPiratasDAO piratasDAO;

    @Override
    public Integer insertarPirata(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento,
            Integer activo, Integer idIsla) {
        return piratasDAO.insertarPirata(id, nombre, frutaDiablo, fechaNacimiento, activo, idIsla);
    }

    @Override
    public ArrayList<PirataDTO> obtenerPiratasPorFiltros(Integer id, String nombre, String frutaDiablo,
            Integer activo) {
        return piratasDAO.obtenerPiratasPorFiltros(id, nombre, frutaDiablo, activo);
    }

    @Override
    public Integer actualizarPirata(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento,
            Integer activo, Integer idIsla) {
        return piratasDAO.actualizarPirata(id, nombre, frutaDiablo, fechaNacimiento, activo, idIsla);
    }

    @Override
    public Integer borrarPirata(Integer id) {
        return piratasDAO.borrarPirata(id);
    }

	@Override
	public ArrayList<PirataDTO> obtenerTodosPiratas() {
		return piratasDAO.obtenerTodosPiratas();
	}
}
