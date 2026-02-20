package com.adrian.onepiece.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reclutamiento")
public class ReclutamientoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pirata_id")
    private PirataEntity pirata;

    @ManyToOne
    @JoinColumn(name = "tripulacion_id")
    private TripulacionEntity tripulacion;

    @Column(name = "rol")
    private String rol;

    @Column(name = "esmiembroactual")
    private Integer esMiembroActual;

    public ReclutamientoEntity() {
    }

    public ReclutamientoEntity(Integer id, PirataEntity pirata, TripulacionEntity tripulacion, String rol,
            Integer esMiembroActual) {
        this.id = id;
        this.pirata = pirata;
        this.tripulacion = tripulacion;
        this.rol = rol;
        this.esMiembroActual = esMiembroActual;
    }

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

    public TripulacionEntity getTripulacion() {
        return tripulacion;
    }

    public void setTripulacion(TripulacionEntity tripulacion) {
        this.tripulacion = tripulacion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getEsMiembroActual() {
        return esMiembroActual;
    }

    public void setEsMiembroActual(Integer esMiembroActual) {
        this.esMiembroActual = esMiembroActual;
    }
}
