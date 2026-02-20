package com.daw.onepiece.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tripulacion")
public class TripulacionEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "barco")
	private String barco;

	@Column(name = "estaActiva")
	private int estaActiva;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany(mappedBy = "tripulacion")
	private List<ReclutamientoEntity> listaReclutamientos = new ArrayList<>();

	public TripulacionEntity() {
		super();
	}

	public TripulacionEntity(int id, String barco, int estaActiva, String nombre,
			List<ReclutamientoEntity> listaReclutamientos) {
		super();
		this.id = id;
		this.barco = barco;
		this.estaActiva = estaActiva;
		this.nombre = nombre;
		this.listaReclutamientos = listaReclutamientos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBarco() {
		return barco;
	}

	public void setBarco(String barco) {
		this.barco = barco;
	}

	public int getEstaActiva() {
		return estaActiva;
	}

	public void setEstaActiva(int estaActiva) {
		this.estaActiva = estaActiva;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ReclutamientoEntity> getListaReclutamientos() {
		return listaReclutamientos;
	}

	public void setListaReclutamientos(List<ReclutamientoEntity> listaReclutamientos) {
		this.listaReclutamientos = listaReclutamientos;
	}

}
