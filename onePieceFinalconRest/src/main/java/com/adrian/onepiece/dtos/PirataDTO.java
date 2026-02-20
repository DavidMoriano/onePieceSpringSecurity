package com.adrian.onepiece.dtos;

import java.time.LocalDate;

import com.adrian.onepiece.entities.IslaEntity;

public class PirataDTO {

    private Integer id;
    private String nombre;
    private String frutaDiablo;
    private LocalDate fechaNacimiento;
    private Integer activo;
    private String isla;
    private Integer idIsla;
    private String tripulacion;
    public String rol;


    public PirataDTO(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento, Integer activo,
            String isla, Integer idIsla) {
        this.id = id;
        this.nombre = nombre;
        this.frutaDiablo = frutaDiablo;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
        this.isla = isla;
        this.idIsla = idIsla;
    }
    
    

    public PirataDTO(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento, Integer activo,
			String isla, Integer idIsla, String tripulacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.frutaDiablo = frutaDiablo;
		this.fechaNacimiento = fechaNacimiento;
		this.activo = activo;
		this.isla = isla;
		this.idIsla = idIsla;
		this.tripulacion = tripulacion;
	}
    
    public PirataDTO(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento, Integer activo,
			String isla) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.frutaDiablo = frutaDiablo;
		this.fechaNacimiento = fechaNacimiento;
		this.activo = activo;
		this.isla = isla;
	}
    
    

	public PirataDTO(Integer id, String nombre, String rol, String frutaDiablo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.frutaDiablo = frutaDiablo;
		this.rol = rol;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFrutaDiablo() {
        return frutaDiablo;
    }

    public void setFrutaDiablo(String frutaDiablo) {
        this.frutaDiablo = frutaDiablo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getIsla() {
        return isla;
    }

    public void setIsla(String isla) {
        this.isla = isla;
    }

    public Integer getIdIsla() {
        return idIsla;
    }

    public void setIdIsla(Integer idIsla) {
        this.idIsla = idIsla;
    }

	public String getTripulacion() {
		return tripulacion;
	}

	public void setTripulacion(String tripulacion) {
		this.tripulacion = tripulacion;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
    
    
}
