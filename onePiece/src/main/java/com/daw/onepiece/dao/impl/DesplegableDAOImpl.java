package com.daw.onepiece.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daw.onepiece.dao.interfaces.IDesplegablesDAO;
import com.daw.onepiece.dtos.DesplegableDTO;
import com.daw.onepiece.entities.IslaEntity;
import com.daw.onepiece.repositorios.IslaRepository;

@Repository
public class DesplegableDAOImpl implements IDesplegablesDAO{
	@Autowired
	private IslaRepository islaRepo;
	
	private ArrayList<DesplegableDTO> mapeoEntidadesIslas(Iterable<IslaEntity> listaEntidadesIsla) {
		ArrayList<DesplegableDTO> listaCombo = new ArrayList<>();
		
		for (IslaEntity isla : listaEntidadesIsla) {
			listaCombo.add(new DesplegableDTO(isla.getId(), isla.getNombre()));
		}
		return listaCombo;
		
	}

	@Override
	public ArrayList<DesplegableDTO> desplegableIslas() {
		Iterable<IslaEntity> listaEntity = islaRepo.findAll();
		ArrayList<DesplegableDTO> listaIslas = mapeoEntidadesIslas(listaEntity);
		return listaIslas;
	}

}
