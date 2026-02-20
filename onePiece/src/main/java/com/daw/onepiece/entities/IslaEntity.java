package com.daw.onepiece.entities;


import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "isla")
public class IslaEntity {
	
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany(mappedBy = "isla")
    private List<PirataEntity> listaPiratas = new ArrayList<>();

	public IslaEntity() {
		super();
	}

	public IslaEntity(int id, String nombre, List<PirataEntity> listaPiratas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.listaPiratas = listaPiratas;
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

	public List<PirataEntity> getListaPiratas() {
		return listaPiratas;
	}

	public void setListaPiratas(List<PirataEntity> listaPiratas) {
		this.listaPiratas = listaPiratas;
	}
	
	
}
