package com.adrian.onepiece.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "barco")
    private String barco;

    @Column(name = "estaactiva")
    private Integer estaActiva;

    @OneToMany(mappedBy = "tripulacion", fetch = FetchType.LAZY)
    private List<ReclutamientoEntity> reclutamientos;

    public TripulacionEntity() {
    }

    public TripulacionEntity(Integer id, String nombre, String barco, Integer estaActiva) {
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

    public List<ReclutamientoEntity> getReclutamientos() {
        return reclutamientos;
    }

    public void setReclutamientos(List<ReclutamientoEntity> reclutamientos) {
        this.reclutamientos = reclutamientos;
    }
}
