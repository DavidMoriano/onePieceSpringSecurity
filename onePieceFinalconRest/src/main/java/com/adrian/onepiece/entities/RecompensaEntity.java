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
@Table(name = "recompensa")
public class RecompensaEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pirata_id")
    private PirataEntity pirata;

    @Column(name = "cantidad")
    private Long cantidad;

    @Column(name = "estavigente")
    private Integer estaVigente;

    public RecompensaEntity() {
    }

    public RecompensaEntity(Integer id, PirataEntity pirata, Long cantidad, Integer estaVigente) {
        this.id = id;
        this.pirata = pirata;
        this.cantidad = cantidad;
        this.estaVigente = estaVigente;
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
}
