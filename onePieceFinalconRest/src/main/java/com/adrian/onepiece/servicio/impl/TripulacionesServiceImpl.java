package com.adrian.onepiece.servicio.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.onepiece.dao.interfaces.ITripulacionesDAO;
import com.adrian.onepiece.dtos.PirataDTO;
import com.adrian.onepiece.dtos.TripulacionDTO;
import com.adrian.onepiece.servicio.interfaces.ITripulacionesService;

@Service
public class TripulacionesServiceImpl implements ITripulacionesService {

    @Autowired
    private ITripulacionesDAO tripulacionesDAO;

    @Override
    public Integer insertarTripulacion(String nombre, String barco, Integer estaActiva) {
        return tripulacionesDAO.insertarTripulacion(nombre, barco, estaActiva);
    }

    @Override
    public ArrayList<TripulacionDTO> listarTripulacionesConMiembros(String nombre, String barco, Integer estaActiva) {
        return tripulacionesDAO.listarTripulacionesConMiembros(nombre, barco, estaActiva);
    }

    @Override
    public TripulacionDTO obtenerTripulacionPorId(Integer id) {
        return tripulacionesDAO.obtenerTripulacionPorId(id);
    }

    @Override
    public ArrayList<PirataDTO> obtenerMiembrosDeTripulacion(Integer idTripulacion) {
        return tripulacionesDAO.obtenerMiembrosDeTripulacion(idTripulacion);
    }

    @Override
    public ArrayList<TripulacionDTO> buscarTripulacionesPorFiltros(Integer id, String nombre, Integer estaActiva) {
        return tripulacionesDAO.buscarTripulacionesPorFiltros(id, nombre, estaActiva);
    }

    @Override
    public Integer actualizarTripulacion(Integer id, String nombre, String barco, Integer estaActiva) {
        return tripulacionesDAO.actualizarTripulacion(id, nombre, barco, estaActiva);
    }

    @Override
    public Integer borrarTripulacion(Integer id) {
        return tripulacionesDAO.borrarTripulacion(id);
    }

    @Override
    public void eliminarMiembroDeTripulacion(Integer idPirata, Integer idTripulacion) {
        tripulacionesDAO.eliminarMiembroDeTripulacion(idPirata, idTripulacion);
    }

    @Override
    public Integer agregarMiembroATripulacion(Integer idPirata, Integer idTripulacion, String rol) {
        return tripulacionesDAO.agregarMiembroATripulacion(idPirata, idTripulacion, rol);
    }

	@Override
	public ArrayList<TripulacionDTO> obtenerTodasTripulaciones() {
		return tripulacionesDAO.obtenerTodasTripulaciones();
	}
}
