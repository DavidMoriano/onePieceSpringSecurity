package com.daw.onepiece.servicio.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.daw.onepiece.dao.interfaces.IRecompensaDAO;
import com.daw.onepiece.servicio.interfaces.IRecompensaService;
import com.daw.onepiece.dtos.RecompensaDTO;

@Service
public class RecompensaServiceImpl implements IRecompensaService {
	@Autowired
	private IRecompensaDAO recompensaDAO;

	@Override
	public ArrayList<RecompensaDTO> listarTodasRecompensas() {
		return recompensaDAO.listarTodasRecompensas();
	}

	@Override
	public ArrayList<RecompensaDTO> buscarRecompensasFiltradas(String nombrePirata, Integer idTripulacion,
			Long cantidadMin, Integer vigente) {
		return recompensaDAO.buscarRecompensasFiltradas(nombrePirata, idTripulacion, cantidadMin, vigente);
	}

	@Transactional
	@Override
	public Integer emitirRecompensa(Integer idPirata, Long cantidad) {
		return recompensaDAO.emitirRecompensa(idPirata, cantidad);
	}

	@Transactional
	@Override
	public int actualizarRecompensa(Integer id, Integer idPirata, Long cantidad, int vigente) {
		return recompensaDAO.actualizarRecompensa(id, idPirata, cantidad, vigente);
	}

	@Transactional
	@Override
	public int marcarRecompensaNoVigente(Integer id) {
		return recompensaDAO.marcarNoVigente(id);
	}

	@Override
	public ArrayList<RecompensaDTO> buscarRecompensasParaActualizar(Integer id, String nombrePirata, Integer vigente) {
		return recompensaDAO.buscarRecompensasParaActualizar(id, nombrePirata, vigente);
	}

	@Override
	public ArrayList<RecompensaDTO> buscarRecompensasParaBorrar(Integer id, String nombrePirata) {
		return recompensaDAO.buscarRecompensasParaBorrar(id, nombrePirata);
	}
}