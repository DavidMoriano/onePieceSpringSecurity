package com.daw.onepiece.servicio.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.onepiece.dao.interfaces.ITripulacionDAO;
import com.daw.onepiece.dtos.MiembroTripulacionDTO;
import com.daw.onepiece.dtos.TripulacionDTO;
import com.daw.onepiece.servicio.interfaces.ITripulacionService;

import jakarta.transaction.Transactional;

@Service
public class TripulacionServiceImpl implements ITripulacionService {

    @Autowired
    private ITripulacionDAO tripuDAO;

    @Override
    public ArrayList<TripulacionDTO> listarTodasTripulacionesService() {
        return tripuDAO.listarTodasTripulaciones();
    }

    @Override
    public Integer insertarTripulacion(String nombreLimpio, String barcoLimpio, int activa) {
        return tripuDAO.insertarTripulacion(nombreLimpio, barcoLimpio, activa);
    }

    @Override
    public TripulacionDTO obtenerTripulacionPorId(Integer id) {
        return tripuDAO.obtenerTripulacionPorId(id);
    }

    @Override
    public ArrayList<MiembroTripulacionDTO> obtenerMiembrosActivosDeTripulacion(Integer id) {
        return tripuDAO.obtenerMiembrosActivosPorTripulacion(id);
    }

    @Transactional
    @Override
    public void agregarMiembroATripulacion(Integer idPirata, Integer idTripulacion, String rolLimpio) {
        tripuDAO.agregarMiembro(idPirata, idTripulacion, rolLimpio);
    }

    @Transactional
    @Override
    public void eliminarMiembroDeTripulacion(Integer idPirata, Integer idTripulacion) {
        tripuDAO.eliminarMiembro(idPirata, idTripulacion);
    }

    @Override
    public int actualizarTripulacion(Integer id, String nombreLimpio, String barcoLimpio, int activa) {
        return tripuDAO.actualizarTripulacion(id, nombreLimpio, barcoLimpio, activa);
    }

    @Override
    public int desactivarTripulacion(Integer id) {
        return tripuDAO.desactivarTripulacion(id);
    }

	@Override
	public ArrayList<TripulacionDTO> listarTripulacionesFiltros(String nombreFiltro, String barcoFiltro, Integer activo) {
		return tripuDAO.listarTripulacionesConFiltros(nombreFiltro, barcoFiltro, activo);
	}


}