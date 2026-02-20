package com.daw.onepiece.dtos;

import java.util.Date;

public class MiembroTripulacionDTO {

	private int id;
	private String nombre;
	private String frutaDelDiablo;
	private String nombreTripulacion;
	private Date fechaNacimiento;
	private String isla;
	private int idIsla;
	private int estaActivo;
	private String rol;

	public MiembroTripulacionDTO(int id, String nombre, String frutaDelDiablo, String nombreTripulacion,
			Date fechaNacimiento, String isla, int idIsla, int estaActivo, String rol) {
		this.id = id;
		this.nombre = nombre;
		this.frutaDelDiablo = frutaDelDiablo;
		this.nombreTripulacion = nombreTripulacion;
		this.fechaNacimiento = fechaNacimiento;
		this.isla = isla;
		this.idIsla = idIsla;
		this.estaActivo = estaActivo;
		this.rol = rol;
	}

	public MiembroTripulacionDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFrutaDelDiablo() {
		return frutaDelDiablo;
	}

	public void setFrutaDelDiablo(String frutaDelDiablo) {
		this.frutaDelDiablo = frutaDelDiablo;
	}

	public String getNombreTripulacion() {
		return nombreTripulacion;
	}

	public void setNombreTripulacion(String nombreTripulacion) {
		this.nombreTripulacion = nombreTripulacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getIsla() {
		return isla;
	}

	public void setIsla(String isla) {
		this.isla = isla;
	}

	public int getIdIsla() {
		return idIsla;
	}

	public void setIdIsla(int idIsla) {
		this.idIsla = idIsla;
	}

	public int getEstaActivo() {
		return estaActivo;
	}

	public void setEstaActivo(int estaActivo) {
		this.estaActivo = estaActivo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
}