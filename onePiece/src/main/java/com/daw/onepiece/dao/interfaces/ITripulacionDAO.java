package com.daw.onepiece.dao.interfaces;

import java.util.ArrayList;

import com.daw.onepiece.dtos.MiembroTripulacionDTO;
import com.daw.onepiece.dtos.TripulacionDTO;

public interface ITripulacionDAO {

    ArrayList<TripulacionDTO> listarTodasTripulaciones();

    ArrayList<TripulacionDTO> listarTripulacionesConFiltros(String nombre, String barco, Integer estaActivo);

    Integer insertarTripulacion(String nombre, String barco, int estaActiva);

    TripulacionDTO obtenerTripulacionPorId(Integer id);

    ArrayList<MiembroTripulacionDTO> obtenerMiembrosActivosPorTripulacion(Integer idTripulacion);

    void agregarMiembro(Integer idPirata, Integer idTripulacion, String rol);

    void eliminarMiembro(Integer idPirata, Integer idTripulacion);

    int actualizarTripulacion(Integer id, String nombre, String barco, int estaActiva);

    int desactivarTripulacion(Integer id);

}