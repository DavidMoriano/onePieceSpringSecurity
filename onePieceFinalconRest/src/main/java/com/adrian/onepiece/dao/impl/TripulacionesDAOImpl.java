package com.adrian.onepiece.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.onepiece.dao.interfaces.ITripulacionesDAO;
import com.adrian.onepiece.dtos.PirataDTO;
import com.adrian.onepiece.dtos.TripulacionDTO;
import com.adrian.onepiece.entities.PirataEntity;
import com.adrian.onepiece.entities.ReclutamientoEntity;
import com.adrian.onepiece.entities.TripulacionEntity;
import com.adrian.onepiece.repositorios.PirataRepository;
import com.adrian.onepiece.repositorios.ReclutamientoRepository;
import com.adrian.onepiece.repositorios.TripulacionRepository;

@Repository
public class TripulacionesDAOImpl implements ITripulacionesDAO {

	@Autowired
	private TripulacionRepository tripulacionRepository;

	@Autowired
	private ReclutamientoRepository reclutamientoRepository;

	@Autowired
	private PirataRepository pirataRepository;

	@Override
	public Integer insertarTripulacion(String nombre, String barco, Integer estaActiva) {
		TripulacionEntity tripulacion = new TripulacionEntity(null, nombre, barco, estaActiva);
		tripulacionRepository.save(tripulacion);
		return tripulacion.getId();
	}

	@Override
	public ArrayList<TripulacionDTO> listarTripulacionesConMiembros(String nombre, String barco, Integer estaActiva) {
		return tripulacionRepository.listarTripulacionesConMiembros(nombre, barco, estaActiva);
	}

	@Override
	public TripulacionDTO obtenerTripulacionPorId(Integer id) {
		TripulacionEntity tripulacion = tripulacionRepository.findById(id).orElse(null);
		if (tripulacion != null) {
			return new TripulacionDTO(tripulacion.getId(), tripulacion.getNombre(), tripulacion.getBarco(),
					tripulacion.getEstaActiva());
		}
		return null;
	}

	@Override
	public ArrayList<PirataDTO> obtenerMiembrosDeTripulacion(Integer idTripulacion) {
		return tripulacionRepository.obtenerMiembrosDeTripulacion(idTripulacion);
	}

	@Override
	public ArrayList<TripulacionDTO> buscarTripulacionesPorFiltros(Integer id, String nombre, Integer estaActiva) {
		return tripulacionRepository.buscarTripulacionesPorFiltros(id, nombre, estaActiva);
	}

	@Override
	public Integer actualizarTripulacion(Integer id, String nombre, String barco, Integer estaActiva) {
		TripulacionEntity tripulacion = new TripulacionEntity(id, nombre, barco, estaActiva);
		tripulacionRepository.save(tripulacion);
		return tripulacion.getId();
	}

	@Override
	public Integer borrarTripulacion(Integer id) {
		TripulacionEntity tripulacion = tripulacionRepository.findById(id).get();
		tripulacion.setEstaActiva(0);
		tripulacionRepository.save(tripulacion);
		return tripulacion.getId();
	}

	@Override
	public void eliminarMiembroDeTripulacion(Integer idPirata, Integer idTripulacion) {
		reclutamientoRepository.eliminarMiembroDeTripulacion(idPirata, idTripulacion);
	}

	@Override
	public Integer agregarMiembroATripulacion(Integer idPirata, Integer idTripulacion, String rol) {
		// Primero desactivamos todos los reclutamientos activos del pirata
		reclutamientoRepository.desactivarReclutamientosDelPirata(idPirata);

		// Obtenemos las entidades necesarias
		PirataEntity pirata = pirataRepository.findById(idPirata).get();
		TripulacionEntity tripulacion = tripulacionRepository.findById(idTripulacion).get();

		// Creamos el nuevo reclutamiento
		ReclutamientoEntity nuevoReclutamiento = new ReclutamientoEntity(null, pirata, tripulacion, rol, 1);
		reclutamientoRepository.save(nuevoReclutamiento);

		return nuevoReclutamiento.getId();
	}

	@Override
	public ArrayList<TripulacionDTO> obtenerTodasTripulaciones() {
		return mapeoEntidadTripulacionDTO(tripulacionRepository.findAll());
	}

	private ArrayList<TripulacionDTO> mapeoEntidadTripulacionDTO(
			Iterable<TripulacionEntity> listaEntidadesTripulaciones) {
		ArrayList<TripulacionDTO> listaTripulaciones = new ArrayList<>();
		for (TripulacionEntity tripulacion : listaEntidadesTripulaciones) {
			listaTripulaciones.add(new TripulacionDTO(tripulacion.getId(), tripulacion.getNombre(),
					tripulacion.getBarco(), tripulacion.getEstaActiva()));
		}
		return listaTripulaciones;
	}
}
