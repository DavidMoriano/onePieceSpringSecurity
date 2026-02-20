package com.adrian.onepiece.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.onepiece.dao.interfaces.IPiratasDAO;
import com.adrian.onepiece.dtos.PirataDTO;
import com.adrian.onepiece.entities.IslaEntity;
import com.adrian.onepiece.entities.PirataEntity;
import com.adrian.onepiece.repositorios.IslaRepository;
import com.adrian.onepiece.repositorios.PirataRepository;

@Repository
public class PiratasDAOImpl implements IPiratasDAO {

	@Autowired
	private IslaRepository islaRepository;

	@Autowired
	private PirataRepository pirataRepository;

	@Override
	public int insertarPirata(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento, Integer activo,
			Integer idIsla) {
		// Encontramos la isla
		IslaEntity isla = islaRepository.findById(idIsla).get();

		// Creamos la entidad Pirata
		PirataEntity pirata = new PirataEntity(id, nombre, frutaDiablo, fechaNacimiento, activo, isla);

		pirataRepository.save(pirata);
		return pirata.getId();
	}

	@Override
	public ArrayList<PirataDTO> obtenerPiratasPorFiltros(Integer id, String nombre, String frutaDiablo,
			Integer activo) {
		return pirataRepository.buscarPiratasPorFiltros(id, nombre, frutaDiablo, activo);
	}

	@Override
	public int actualizarPirata(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento,
			Integer activo, Integer idIsla) {
		// Encontramos la isla
		IslaEntity isla = islaRepository.findById(idIsla).get();

		// Creamos la entidad Pirata
		PirataEntity pirata = new PirataEntity(id, nombre, frutaDiablo, fechaNacimiento, activo, isla);

		pirataRepository.save(pirata);
		return pirata.getId();
	}

	@Override
	public int borrarPirata(Integer id) {
		PirataEntity pirata = pirataRepository.findById(id).get();
		pirata.setActivo(0);
		pirataRepository.save(pirata);
		return pirata.getId();
	}

	@Override
	public ArrayList<PirataDTO> obtenerTodosPiratas() {
		return mapeoEntidadPirataDTO(pirataRepository.findAll());
	}

	private ArrayList<PirataDTO> mapeoEntidadPirataDTO(Iterable<PirataEntity> listaEntidadesPiratas) {
		ArrayList<PirataDTO> listaPiratas = new ArrayList<>();
		for (PirataEntity pirata : listaEntidadesPiratas) {
			listaPiratas.add(new PirataDTO(pirata.getId(), pirata.getNombre(), pirata.getFrutaDiablo(),
					pirata.getFechaNacimiento(), pirata.getActivo(), pirata.getIsla().getNombre()));
		}
		return listaPiratas;
	}

}
