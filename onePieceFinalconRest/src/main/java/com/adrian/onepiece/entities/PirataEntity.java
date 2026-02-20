package com.adrian.onepiece.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pirata")
public class PirataEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "frutadeldiablo")
    private String frutaDiablo;

    @Column(name = "fechanacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "estaactivo")
    private Integer activo;

    @ManyToOne
    @JoinColumn(name = "isla_id")
    private IslaEntity isla;

    @OneToMany(mappedBy = "pirata", fetch = FetchType.LAZY)
    private List<ReclutamientoEntity> reclutamientos;
    
    @OneToMany(mappedBy = "pirata", fetch = FetchType.LAZY)
    private List<RecompensaEntity> recompensas;

    public PirataEntity() {
    }

    public PirataEntity(Integer id, String nombre, String frutaDiablo, LocalDate fechaNacimiento, Integer activo,
            IslaEntity isla) {
        this.id = id;
        this.nombre = nombre;
        this.frutaDiablo = frutaDiablo;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
        this.isla = isla;
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

    public IslaEntity getIsla() {
        return isla;
    }

    public void setIsla(IslaEntity isla) {
        this.isla = isla;
    }

    public List<ReclutamientoEntity> getReclutamientos() {
        return reclutamientos;
    }

    public void setReclutamientos(List<ReclutamientoEntity> reclutamientos) {
        this.reclutamientos = reclutamientos;
    }
}
