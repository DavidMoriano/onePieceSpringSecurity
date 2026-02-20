package com.daw.onepiece.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.daw.onepiece.dao.interfaces.ITripulacionDAO;
import com.daw.onepiece.dtos.MiembroTripulacionDTO;
import com.daw.onepiece.dtos.TripulacionDTO;
import com.daw.onepiece.entities.PirataEntity;
import com.daw.onepiece.entities.ReclutamientoEntity;
import com.daw.onepiece.entities.TripulacionEntity;
import com.daw.onepiece.repositorios.PirataRepository;
import com.daw.onepiece.repositorios.ReclutamientoRepository;
import com.daw.onepiece.repositorios.TripulacionRepository;

@Repository
public class TripulacionDAOImpl implements ITripulacionDAO {

    @Autowired
    private TripulacionRepository tripulacionRepo;

    @Autowired
    private ReclutamientoRepository reclutamientoRepo;
    
    @Autowired
    private PirataRepository pirataRepo;


    @Override
    public ArrayList<TripulacionDTO> listarTodasTripulaciones() {
        return tripulacionRepo.findAllConCantidadReclutados();
    }

    @Override
    public ArrayList<TripulacionDTO> listarTripulacionesConFiltros(String nombre, String barco, Integer estaActivo) {
        return tripulacionRepo.buscarTripulacionesFiltradas(null, nombre, barco, estaActivo);
    }

    @Override
    public Integer insertarTripulacion(String nombre, String barco, int estaActiva) {
        TripulacionEntity nueva = new TripulacionEntity();
        nueva.setNombre(nombre);
        nueva.setBarco(barco);
        nueva.setEstaActiva(estaActiva);
        tripulacionRepo.save(nueva);
        return nueva.getId();
    }

    @Override
    public TripulacionDTO obtenerTripulacionPorId(Integer id) {
        return tripulacionRepo.findById(id)
                .map(entity -> {
                    int cantidad = reclutamientoRepo.contarMiembrosActivos(id);
                    return new TripulacionDTO(
                        entity.getId(),
                        entity.getNombre(),
                        entity.getBarco(),
                        entity.getEstaActiva(),
                        cantidad
                    );
                })
                .orElse(null);
    }

    @Override
    public ArrayList<MiembroTripulacionDTO> obtenerMiembrosActivosPorTripulacion(Integer idTripulacion) {
        return reclutamientoRepo.obtenerPiratasActivosDeTripulacion(idTripulacion);
    }

    @Override
    public void agregarMiembro(Integer idPirata, Integer idTripulacion, String rol) {
        reclutamientoRepo.desactivarMiembroActual(idPirata);
        PirataEntity pirata = pirataRepo.findById(idPirata).orElseThrow(() -> new IllegalArgumentException("Piarata no encontrado: " + idPirata));
        TripulacionEntity tripulacion = tripulacionRepo.findById(idTripulacion).orElseThrow(() -> new IllegalArgumentException("Tripulacion no encontrada: " + idTripulacion));
        
        
        ReclutamientoEntity nuevo = new ReclutamientoEntity();
                
        nuevo.setPirata(pirata);
        nuevo.setEsMiembroActual(1);
        nuevo.setTripulacion(tripulacion);
        nuevo.setRol(rol);
        reclutamientoRepo.save(nuevo);
    }

    @Override
    public void eliminarMiembro(Integer idPirata, Integer idTripulacion) {
        reclutamientoRepo.desactivarMiembroDeTripulacion(idPirata, idTripulacion);
    }

    @Override
    public int actualizarTripulacion(Integer id, String nombre, String barco, int estaActiva) {
        return tripulacionRepo.findById(id)
                .map(t -> {
                    t.setNombre(nombre);
                    t.setBarco(barco);
                    t.setEstaActiva(estaActiva);
                    tripulacionRepo.save(t);
                    return t.getId();
                })
                .orElse(0);
    }

    @Override
    public int desactivarTripulacion(Integer id) {
        return tripulacionRepo.findById(id)
                .map(t -> {
                    t.setEstaActiva(0);
                    tripulacionRepo.save(t);
                    return t.getId();
                })
                .orElse(0);
    }
}