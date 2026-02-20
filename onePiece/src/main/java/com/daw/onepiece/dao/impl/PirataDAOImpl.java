package com.daw.onepiece.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daw.onepiece.dao.interfaces.IPirataDAO;
import com.daw.onepiece.dtos.PirataDTO;
import com.daw.onepiece.entities.IslaEntity;
import com.daw.onepiece.entities.PirataEntity;
import com.daw.onepiece.repositorios.IslaRepository;
import com.daw.onepiece.repositorios.PirataRepository;

@Repository
public class PirataDAOImpl implements IPirataDAO {

	@Autowired
	private PirataRepository pirataRepo;

	@Autowired
	private IslaRepository islaRepo;

	@Override
	public ArrayList<PirataDTO> listarTodosLosPiratas() {
		return pirataRepo.listarTodosPiratasSinFiltros();
	}

	@Override
	public PirataDTO obtenerPirataPorId(Integer id) {
	    return pirataRepo.findById(id)
	            .map(entity -> new PirataDTO(
	                entity.getId(),
	                entity.getNombre(),
	                entity.getFrutaDelDiablo(),
	                null,
	                entity.getFechaNacimiento(),
	                entity.getIsla() != null ? entity.getIsla().getNombre() : null,
	                entity.getIsla() != null ? entity.getIsla().getId() : 0,
	                entity.isEstaActivo()
	            ))
	            .orElse(null);
	}

	@Override
	public ArrayList<PirataDTO> listarPiratasConFiltros(Integer id, String nombrePirata, String frutaDiablo,
			int activoInt) {
		return pirataRepo.listarPiratasConFiltros(id, nombrePirata, frutaDiablo, activoInt);
	}

	@Override
	public int insertarPirata(String nombrePirata, String frutaDiablo, Date fecha, int idIsla, int activoInt) {
		IslaEntity isla = islaRepo.findById(idIsla)
				.orElseThrow(() -> new IllegalArgumentException("Isla no encontrada: " + idIsla));

		PirataEntity nuevoPirata = new PirataEntity(nombrePirata, frutaDiablo, fecha, isla, activoInt);

		pirataRepo.save(nuevoPirata);

		return nuevoPirata.getId();
	}

	@Override
	public int actualizarPirata(Integer id, String nombrePirata, String frutaDiablo, String idIsla, Date fechaFiltro,
			int activo) {
		PirataEntity pirata = pirataRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Piarata no encontrado: " + id));

		pirata.setNombre(nombrePirata);
		pirata.setFrutaDelDiablo(frutaDiablo);
		pirata.setEstaActivo(activo);
		pirata.setFechaNacimiento(fechaFiltro);

		pirataRepo.save(pirata);
		return pirata.getId();
	}

	@Override
	public int borrarPirata(Integer id) {
		PirataEntity pirata = pirataRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Piarata no encontrado: " + id));

		pirata.setEstaActivo(0);

		pirataRepo.save(pirata);
		return pirata.getId();
	}

	@Override
	public ArrayList<PirataDTO> listarPiratasActivosNoEnTripulacion(Integer id) {
		return pirataRepo.listarPiratasSinReclutamiento();
	}

}
