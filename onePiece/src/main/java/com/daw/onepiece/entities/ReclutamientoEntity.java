package com.daw.onepiece.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reclutamiento")
public class ReclutamientoEntity {
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "rol")
	private String rol;

	@Column(name = "esMiembroActual")
	private int esMiembroActual;

	@ManyToOne
	@JoinColumn(name = "pirata_id")
	private PirataEntity pirata;

	@ManyToOne
	@JoinColumn(name = "tripulacion_id")
	private TripulacionEntity tripulacion;

	public ReclutamientoEntity() {
		super();
	}

	public ReclutamientoEntity(int id, String rol, int esMiembroActual, PirataEntity pirata,
			TripulacionEntity tripulacion) {
		super();
		this.id = id;
		this.rol = rol;
		this.esMiembroActual = esMiembroActual;
		this.pirata = pirata;
		this.tripulacion = tripulacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getEsMiembroActual() {
		return esMiembroActual;
	}

	public void setEsMiembroActual(int esMiembroActual) {
		this.esMiembroActual = esMiembroActual;
	}

	public PirataEntity getPirata() {
		return pirata;
	}

	public void setPirata(PirataEntity pirata) {
		this.pirata = pirata;
	}

	public TripulacionEntity getTripulacion() {
		return tripulacion;
	}

	public void setTripulacion(TripulacionEntity tripulacion) {
		this.tripulacion = tripulacion;
	}

}
