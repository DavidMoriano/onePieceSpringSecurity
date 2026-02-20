package com.daw.onepiece.dtos;

public class RecompensaDTO {
	private int id;
	private int idPirata;
	private String nombrePirata;
	private String tripulacion;
	private Long cantidad;
	private int estaVigente;

	public RecompensaDTO(int id, int idPirata, String nombrePirata, String tripulacion, Long cantidad, int estaVigente) {
		this.id = id;
		this.idPirata = idPirata;
		this.nombrePirata = nombrePirata;
		this.tripulacion = tripulacion;
		this.cantidad = cantidad;
		this.estaVigente = estaVigente;
	}

	public RecompensaDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPirata() {
		return idPirata;
	}

	public void setIdPirata(int idPirata) {
		this.idPirata = idPirata;
	}

	public String getNombrePirata() {
		return nombrePirata;
	}

	public void setNombrePirata(String nombrePirata) {
		this.nombrePirata = nombrePirata;
	}

	public String getTripulacion() {
		return tripulacion;
	}

	public void setTripulacion(String tripulacion) {
		this.tripulacion = tripulacion;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public int getEstaVigente() {
		return estaVigente;
	}

	public void setEstaVigente(int estaVigente) {
		this.estaVigente = estaVigente;
	}
}