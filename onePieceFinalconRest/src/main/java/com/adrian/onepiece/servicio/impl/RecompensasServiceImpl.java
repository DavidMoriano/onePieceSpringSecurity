package com.adrian.onepiece.servicio.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.onepiece.dao.interfaces.IRecompensasDAO;
import com.adrian.onepiece.dtos.RecompensaDTO;
import com.adrian.onepiece.servicio.interfaces.IRecompensasService;

@Service
public class RecompensasServiceImpl implements IRecompensasService {

    @Autowired
    private IRecompensasDAO recompensasDAO;

    @Override
    public Integer emitirRecompensa(Integer idPirata, Long cantidad) throws Exception {
        return recompensasDAO.emitirRecompensa(idPirata, cantidad);
    }

    @Override
    public ArrayList<RecompensaDTO> listarRecompensasConFiltros(String nombrePirata, Integer idTripulacion,
            Long cantidad, Integer estaVigente) {
        return recompensasDAO.listarRecompensasConFiltros(nombrePirata, idTripulacion, cantidad, estaVigente);
    }

    @Override
    public ArrayList<RecompensaDTO> buscarRecompensasPorFiltros(Integer id, String nombrePirata,
            Integer estaVigente) {
        return recompensasDAO.buscarRecompensasPorFiltros(id, nombrePirata, estaVigente);
    }

    @Override
    public Integer actualizarRecompensa(Integer id, Integer idPirata, Long cantidad, Integer estaVigente) {
        return recompensasDAO.actualizarRecompensa(id, idPirata, cantidad, estaVigente);
    }

    @Override
    public Integer eliminarRecompensa(Integer id) {
        return recompensasDAO.eliminarRecompensa(id);
    }

	@Override
	public ArrayList<RecompensaDTO> obtenerTodasRecompensas() {
		return recompensasDAO.obtenerTodasRecompensas();
	}
}
