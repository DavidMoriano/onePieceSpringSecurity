package com.adrian.onepiece.servicio.interfaces;

import java.util.ArrayList;

import com.adrian.onepiece.dtos.RecompensaDTO;

public interface IRecompensasService {

    Integer emitirRecompensa(Integer idPirata, Long cantidad) throws Exception;

    ArrayList<RecompensaDTO> listarRecompensasConFiltros(String nombrePirata, Integer idTripulacion, Long cantidad,
            Integer estaVigente);

    ArrayList<RecompensaDTO> buscarRecompensasPorFiltros(Integer id, String nombrePirata, Integer estaVigente);

    Integer actualizarRecompensa(Integer id, Integer idPirata, Long cantidad, Integer estaVigente);

    Integer eliminarRecompensa(Integer id);
    
    public ArrayList<RecompensaDTO> obtenerTodasRecompensas();
}
