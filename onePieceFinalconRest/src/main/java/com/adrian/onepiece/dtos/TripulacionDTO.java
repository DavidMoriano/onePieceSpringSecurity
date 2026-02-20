package com.adrian.onepiece.dtos;

public class TripulacionDTO {

    private Integer id;
    private String nombre;
    private String barco;
    private Integer estaActiva;
    private Long numeroMiembros;

    public TripulacionDTO() {
    }

    // Constructor para listado con número de miembros
    public TripulacionDTO(Integer id, String nombre, String barco, Integer estaActiva, Long numeroMiembros) {
        this.id = id;
        this.nombre = nombre;
        this.barco = barco;
        this.estaActiva = estaActiva;
        this.numeroMiembros = numeroMiembros;
    }

    // Constructor para formularios (sin número de miembros)
    public TripulacionDTO(Integer id, String nombre, String barco, Integer estaActiva) {
        this.id = id;
        this.nombre = nombre;
        this.barco = barco;
        this.estaActiva = estaActiva;
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

    public String getBarco() {
        return barco;
    }

    public void setBarco(String barco) {
        this.barco = barco;
    }

    public Integer getEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(Integer estaActiva) {
        this.estaActiva = estaActiva;
    }

    public Long getNumeroMiembros() {
        return numeroMiembros;
    }

    public void setNumeroMiembros(Long numeroMiembros) {
        this.numeroMiembros = numeroMiembros;
    }
}
