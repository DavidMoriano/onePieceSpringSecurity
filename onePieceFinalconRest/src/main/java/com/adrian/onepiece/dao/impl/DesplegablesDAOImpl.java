package com.adrian.onepiece.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.onepiece.dao.interfaces.IDesplegablesDAO;
import com.adrian.onepiece.dtos.DesplegableDTO;
import com.adrian.onepiece.entities.IslaEntity;
import com.adrian.onepiece.entities.PirataEntity;
import com.adrian.onepiece.entities.TripulacionEntity;
import com.adrian.onepiece.repositorios.IslaRepository;
import com.adrian.onepiece.repositorios.PirataRepository;
import com.adrian.onepiece.repositorios.TripulacionRepository;

@Repository
public class DesplegablesDAOImpl implements IDesplegablesDAO {

    @Autowired
    private IslaRepository islaRepository;

    @Autowired
    private PirataRepository pirataRepository;

    @Autowired
    private TripulacionRepository tripulacionRepository;

    @Override
    public ArrayList<DesplegableDTO> desplegableIslas() {
        ArrayList<DesplegableDTO> listaIslas = new ArrayList<>();

        Iterable<IslaEntity> islas = islaRepository.findAll();

        for (IslaEntity isla : islas) {
            listaIslas.add(new DesplegableDTO(isla.getId(), isla.getNombre()));
        }

        return listaIslas;
    }

    @Override
    public ArrayList<DesplegableDTO> desplegablePiratasActivos() {
        ArrayList<DesplegableDTO> listaPiratas = new ArrayList<>();

        Iterable<PirataEntity> piratas = pirataRepository.findAll();

        for (PirataEntity pirata : piratas) {
            if (pirata.getActivo() == 1) {
                listaPiratas.add(new DesplegableDTO(pirata.getId(), pirata.getNombre()));
            }
        }

        return listaPiratas;
    }

    @Override
    public ArrayList<DesplegableDTO> desplegableTripulacionesActivas() {
        ArrayList<DesplegableDTO> listaTripulaciones = new ArrayList<>();

        Iterable<TripulacionEntity> tripulaciones = tripulacionRepository.findAll();

        for (TripulacionEntity tripulacion : tripulaciones) {
            if (tripulacion.getEstaActiva() == 1) {
                listaTripulaciones.add(new DesplegableDTO(tripulacion.getId(), tripulacion.getNombre()));
            }
        }

        return listaTripulaciones;
    }
}
