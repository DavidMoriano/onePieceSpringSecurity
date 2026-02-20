package com.daw.onepiece.servicio.interfaces;

import java.util.ArrayList;

import com.daw.onepiece.dtos.MiembroTripulacionDTO;
import com.daw.onepiece.dtos.TripulacionDTO;

public interface ITripulacionService {

	ArrayList<TripulacionDTO> listarTodasTripulacionesService();

	ArrayList<TripulacionDTO> listarTripulacionesFiltros(String nombreFiltro, String barcoFiltro, Integer activo);

	Integer insertarTripulacion(String nombreLimpio, String barcoLimpio, int activa);

	TripulacionDTO obtenerTripulacionPorId(Integer id);

	ArrayList<MiembroTripulacionDTO> obtenerMiembrosActivosDeTripulacion(Integer id);

	void agregarMiembroATripulacion(Integer idPirata, Integer idTripulacion, String rolLimpio);

	void eliminarMiembroDeTripulacion(Integer idPirata, Integer idTripulacion);

	int actualizarTripulacion(Integer id, String nombreLimpio, String barcoLimpio, int activa);
	
	int desactivarTripulacion(Integer id);

}
