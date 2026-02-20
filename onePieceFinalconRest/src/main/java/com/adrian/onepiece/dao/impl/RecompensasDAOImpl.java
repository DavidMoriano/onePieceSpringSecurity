package com.adrian.onepiece.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adrian.onepiece.dao.interfaces.IRecompensasDAO;
import com.adrian.onepiece.dtos.PirataDTO;
import com.adrian.onepiece.dtos.RecompensaDTO;
import com.adrian.onepiece.entities.PirataEntity;
import com.adrian.onepiece.entities.RecompensaEntity;
import com.adrian.onepiece.entities.TesoreriaEntity;
import com.adrian.onepiece.repositorios.PirataRepository;
import com.adrian.onepiece.repositorios.RecompensaRepository;
import com.adrian.onepiece.repositorios.TesoreriaRepository;

@Repository
public class RecompensasDAOImpl implements IRecompensasDAO {

	@Autowired
	private RecompensaRepository recompensaRepository;

	@Autowired
	private PirataRepository pirataRepository;

	@Autowired
	private TesoreriaRepository tesoreriaRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer emitirRecompensa(Integer idPirata, Long cantidad) throws Exception {
		try {
			// 1. Desactivar recompensas anteriores del pirata
			recompensaRepository.desactivarRecompensasDelPirata(idPirata);

			// 2. Crear la nueva recompensa
			PirataEntity pirata = pirataRepository.findById(idPirata).get();
			RecompensaEntity nuevaRecompensa = new RecompensaEntity(null, pirata, cantidad, 1);
			recompensaRepository.save(nuevaRecompensa);

			// 3. Crear registro en tesorería (costo de emisión: 10,000 berries)
			TesoreriaEntity registroTesoreria = new TesoreriaEntity(null, "EMISION RECOMPENSA", -10000L,
					LocalDateTime.now());
			tesoreriaRepository.save(registroTesoreria);

			return nuevaRecompensa.getId();
		} catch (Exception e) {
			throw new Exception("Error al emitir recompensa: " + e.getMessage(), e);
		}
	}

	@Override
	public ArrayList<RecompensaDTO> listarRecompensasConFiltros(String nombrePirata, Integer idTripulacion,
			Long cantidad, Integer estaVigente) {
		return recompensaRepository.listarRecompensasConFiltros(nombrePirata, idTripulacion, cantidad, estaVigente);
	}

	@Override
	public ArrayList<RecompensaDTO> buscarRecompensasPorFiltros(Integer id, String nombrePirata, Integer estaVigente) {
		return recompensaRepository.buscarRecompensasPorFiltros(id, nombrePirata, estaVigente);
	}

	@Override
	public Integer actualizarRecompensa(Integer id, Integer idPirata, Long cantidad, Integer estaVigente) {
		PirataEntity pirata = pirataRepository.findById(idPirata).get();
		RecompensaEntity recompensa = new RecompensaEntity(id, pirata, cantidad, estaVigente);
		recompensaRepository.save(recompensa);
		return recompensa.getId();
	}

	@Override
	public Integer eliminarRecompensa(Integer id) {
		RecompensaEntity recompensa = recompensaRepository.findById(id).get();
		recompensa.setEstaVigente(0);
		recompensaRepository.save(recompensa);
		return recompensa.getId();
	}

	@Override
	public ArrayList<RecompensaDTO> obtenerTodasRecompensas() {
		return mapeoEntidadRecompensaDTO(recompensaRepository.findAll());
	}

	private ArrayList<RecompensaDTO> mapeoEntidadRecompensaDTO(Iterable<RecompensaEntity> listaEntidadesRecompensas) {
		ArrayList<RecompensaDTO> listaRecompensas = new ArrayList<>();
		for (RecompensaEntity recompensa : listaEntidadesRecompensas) {
			listaRecompensas.add(new RecompensaDTO(recompensa.getId(), recompensa.getPirata().getId(),
					recompensa.getPirata().getNombre(), recompensa.getCantidad(), recompensa.getEstaVigente()));
		}
		return listaRecompensas;
	}
}
