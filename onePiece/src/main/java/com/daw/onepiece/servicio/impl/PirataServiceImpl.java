package com.daw.onepiece.servicio.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.onepiece.dao.interfaces.IPirataDAO;
import com.daw.onepiece.dtos.PirataDTO;
import com.daw.onepiece.servicio.interfaces.IPirataService;

@Service
public class PirataServiceImpl implements IPirataService {

	@Autowired
	private IPirataDAO pirataDAO;
	
	@Override
	public ArrayList<PirataDTO> listarTodosPiratasService() {
		return pirataDAO.listarTodosLosPiratas();
	}

	@Override
	public ArrayList<PirataDTO> listarPiratasFiltros(Integer id, String nombrePirata, String frutaDiablo,
			int activoInt) {
		return pirataDAO.listarPiratasConFiltros(id, nombrePirata, frutaDiablo, activoInt);
	}

	@Override
	public int insertarPirata(String nombrePirata, String frutaDiablo, Date fecha, int idIsla, int activoInt) {
		
		return pirataDAO.insertarPirata(nombrePirata, frutaDiablo, fecha, idIsla, activoInt);
	}
	

	@Override
	public int actualizarPirata(Integer id, String nombrePirata, String frutaDiablo, String idIsla, Date fechaFiltro, int activo) {
		return pirataDAO.actualizarPirata(id, nombrePirata, frutaDiablo, idIsla, fechaFiltro, activo);
	}

	@Override
	public int borrarPirata(Integer id) {
		return pirataDAO.borrarPirata(id);
	}

	@Override
	public ArrayList<PirataDTO> listarPiratasActivosNoEnTripulacion(Integer id) {
		return pirataDAO.listarPiratasActivosNoEnTripulacion(id);
	}


	@Override
	public PirataDTO obtenerPirataPorId(Integer id) {
		return pirataDAO.obtenerPirataPorId(id);
	}

	
}
