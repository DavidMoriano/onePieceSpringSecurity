package com.daw.onepiece.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recompensa")
public class RecompensaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pirata_id", nullable = false)
	private PirataEntity pirata;

	@Column(name = "cantidad", nullable = false)
	private Long cantidad;

	@Column(name = "estaVigente", nullable = false)
	private Integer estaVigente = 1; 
	
	public RecompensaEntity() {
	}

	public RecompensaEntity(PirataEntity pirata, Long cantidad) {
		this.pirata = pirata;
		this.cantidad = cantidad;
		this.estaVigente = 1;
	}

	// Getters y Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PirataEntity getPirata() {
		return pirata;
	}

	public void setPirata(PirataEntity pirata) {
		this.pirata = pirata;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getEstaVigente() {
		return estaVigente;
	}

	public void setEstaVigente(Integer estaVigente) {
		this.estaVigente = estaVigente;
	}

	public boolean isVigente() {
		return estaVigente != null && estaVigente == 1;
	}
}