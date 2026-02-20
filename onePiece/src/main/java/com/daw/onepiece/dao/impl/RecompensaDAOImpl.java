package com.daw.onepiece.dao.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.daw.onepiece.dao.interfaces.IRecompensaDAO;
import com.daw.onepiece.entities.RecompensaEntity;
import com.daw.onepiece.repositorios.RecompensaRepository;
import com.daw.onepiece.repositorios.PirataRepository;
import com.daw.onepiece.dtos.RecompensaDTO;

@Repository
public class RecompensaDAOImpl implements IRecompensaDAO {
	@Autowired
	private RecompensaRepository recompensaRepo;
	@Autowired
	private PirataRepository pirataRepo;

	@Override
	public ArrayList<RecompensaDTO> listarTodasRecompensas() {
		return recompensaRepo.findAllRecompensas();
	}

	@Override
	public ArrayList<RecompensaDTO> buscarRecompensasFiltradas(String nombrePirata, Integer idTripulacion,
			Long cantidadMin, Integer vigente) {
		return recompensaRepo.buscarRecompensasFiltradas(nombrePirata, idTripulacion, cantidadMin, vigente);
	}

	@Override
	public Integer emitirRecompensa(Integer idPirata, Long cantidad) {
		recompensaRepo.desactivarRecompensasVigentesDePirata(idPirata);
		RecompensaEntity nueva = new RecompensaEntity();
		nueva.setPirata(pirataRepo.findById(idPirata).orElse(null));
		nueva.setCantidad(cantidad);
		nueva.setEstaVigente(1);
		recompensaRepo.save(nueva);
		return nueva.getId();
	}

	@Override
	public int actualizarRecompensa(Integer id, Integer idPirata, Long cantidad, int vigente) {
		return recompensaRepo.actualizarRecompensa(id, cantidad, vigente);
	}

	@Override
	public int marcarNoVigente(Integer id) {
		return recompensaRepo.marcarNoVigente(id);
	}

	@Override
	public ArrayList<RecompensaDTO> buscarRecompensasParaActualizar(Integer id, String nombrePirata, Integer vigente) {
		return recompensaRepo.buscarRecompensasFiltradas(nombrePirata, null, null, vigente);
	}

	@Override
	public ArrayList<RecompensaDTO> buscarRecompensasParaBorrar(Integer id, String nombrePirata) {
		return recompensaRepo.buscarRecompensasFiltradas(nombrePirata, null, null, 1);
	}
}