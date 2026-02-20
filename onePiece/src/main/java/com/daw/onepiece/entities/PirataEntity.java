package com.daw.onepiece.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pirata")
public class PirataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "frutaDelDiablo")
	private String frutaDelDiablo;

	@Column(name = "fechaNacimiento")
	private Date fechaNacimiento;

	@Column(name = "estaActivo")
	private int estaActivo;

	@ManyToOne
	@JoinColumn(name = "isla_id")
	private IslaEntity isla;

	@OneToMany(mappedBy = "pirata")
	private List<ReclutamientoEntity> reclutamiento;

	public PirataEntity() {
		super();
	}

	public PirataEntity(int id, String nombre, String frutaDelDiablo, Date fechaNacimiento, int estaActivo,
			IslaEntity isla, List<ReclutamientoEntity> reclutamiento) {
		this.id = id;
		this.nombre = nombre;
		this.frutaDelDiablo = frutaDelDiablo;
		this.fechaNacimiento = fechaNacimiento;
		this.estaActivo = estaActivo;
		this.isla = isla;
		this.reclutamiento = reclutamiento;
	}

	public PirataEntity(String nombre, String frutaDelDiablo, Date fechaNacimiento, IslaEntity isla, int estaActivo) {
		super();
		this.nombre = nombre;
		this.frutaDelDiablo = frutaDelDiablo;
		this.fechaNacimiento = fechaNacimiento;
		this.estaActivo = estaActivo;
		this.isla = isla;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int isEstaActivo() {
		return estaActivo;
	}

	public void setEstaActivo(int estaActivo) {
		this.estaActivo = estaActivo;
	}

	public IslaEntity getIsla() {
		return isla;
	}

	public void setIsla(IslaEntity isla) {
		this.isla = isla;
	}

	public List<ReclutamientoEntity> getReclutamiento() {
		return reclutamiento;
	}

	public void setReclutamiento(List<ReclutamientoEntity> reclutamiento) {
		this.reclutamiento = reclutamiento;
	}
}