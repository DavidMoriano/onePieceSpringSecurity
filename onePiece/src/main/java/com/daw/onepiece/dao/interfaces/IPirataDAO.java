package com.daw.onepiece.dao.interfaces;

import java.util.ArrayList;
import java.util.Date;

import com.daw.onepiece.dtos.PirataDTO;

public interface IPirataDAO {
	public ArrayList<PirataDTO> listarTodosLosPiratas();

	public ArrayList<PirataDTO> listarPiratasConFiltros(Integer id, String nombrePirata, String frutaDiablo,
			int activoInt);

	public int insertarPirata(String nombrePirata, String frutaDiablo, Date fecha, int idIsla, int activoInt);

	public int actualizarPirata(Integer id, String nombrePirata, String frutaDiablo, String idIsla, Date fechaFiltro,
			int activo);

	public int borrarPirata(Integer id);

	public ArrayList<PirataDTO> listarPiratasActivosNoEnTripulacion(Integer id);

	public PirataDTO obtenerPirataPorId(Integer id);
}
