package com.daw.onepiece.dtos;

import java.util.Date;

public class PirataDTO {

	private int id;
	private String nombre;
	private String frutaDelDiablo;
	private Date fechaNacimiento;
	private int estaActivo;
	private String isla;
	private int idIsla;
	private String nombreTripulacion;

	public PirataDTO(int id, String nombre, String frutaDelDiablo, String nombreTripulacion, Date fechaNacimiento,
			String isla, int idIsla, int estaActivo) {
		this.id = id;
		this.nombre = nombre;
		this.frutaDelDiablo = frutaDelDiablo;
		this.nombreTripulacion = nombreTripulacion;
		this.fechaNacimiento = fechaNacimiento;
		this.isla = isla;
		this.idIsla = idIsla;
		this.estaActivo = estaActivo;
	}

	public PirataDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getFrutaDelDiablo() {
		return frutaDelDiablo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public int getEstaActivo() {
		return estaActivo;
	}

	public String getIsla() {
		return isla;
	}

	public String getNombreTripulacion() {
		return nombreTripulacion;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFrutaDelDiablo(String frutaDelDiablo) {
		this.frutaDelDiablo = frutaDelDiablo;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setEstaActivo(int estaActivo) {
		this.estaActivo = estaActivo;
	}

	public void setIsla(String isla) {
		this.isla = isla;
	}

	public void setNombreTripulacion(String nombreTripulacion) {
		this.nombreTripulacion = nombreTripulacion;
	}

	public int getIdIsla() {
		return idIsla;
	}

	public void setIdIsla(int idIsla) {
		this.idIsla = idIsla;
	}
}