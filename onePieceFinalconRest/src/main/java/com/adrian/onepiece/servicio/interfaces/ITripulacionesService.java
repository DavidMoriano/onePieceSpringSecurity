package com.adrian.onepiece.servicio.interfaces;

import java.util.ArrayList;

import com.adrian.onepiece.dtos.PirataDTO;
import com.adrian.onepiece.dtos.TripulacionDTO;

public interface ITripulacionesService {

    Integer insertarTripulacion(String nombre, String barco, Integer estaActiva);

    ArrayList<TripulacionDTO> listarTripulacionesConMiembros(String nombre, String barco, Integer estaActiva);

    TripulacionDTO obtenerTripulacionPorId(Integer id);

    ArrayList<PirataDTO> obtenerMiembrosDeTripulacion(Integer idTripulacion);

    ArrayList<TripulacionDTO> buscarTripulacionesPorFiltros(Integer id, String nombre, Integer estaActiva);

    Integer actualizarTripulacion(Integer id, String nombre, String barco, Integer estaActiva);

    Integer borrarTripulacion(Integer id);

    void eliminarMiembroDeTripulacion(Integer idPirata, Integer idTripulacion);

    Integer agregarMiembroATripulacion(Integer idPirata, Integer idTripulacion, String rol);
    
    public ArrayList<TripulacionDTO> obtenerTodasTripulaciones();
}
