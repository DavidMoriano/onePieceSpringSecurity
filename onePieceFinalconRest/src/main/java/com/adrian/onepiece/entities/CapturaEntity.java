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
@Table(name = "captura")
public class CapturaEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pirata_id")
    private PirataEntity pirata;

    @Column(name = "lugarcaptura")
    private String lugarCaptura;

    @Column(name = "recompensacobrada")
    private Long recompensaCobrada;

    public CapturaEntity() {
    }

    public CapturaEntity(Integer id, PirataEntity pirata, String lugarCaptura,
    		Long recompensaCobrada) {
        this.id = id;
        this.pirata = pirata;
        this.lugarCaptura = lugarCaptura;
        this.recompensaCobrada = recompensaCobrada;
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

    public String getLugarCaptura() {
        return lugarCaptura;
    }

    public void setLugarCaptura(String lugarCaptura) {
        this.lugarCaptura = lugarCaptura;
    }


    public Long getRecompensaCobrada() {
        return recompensaCobrada;
    }

    public void setRecompensaCobrada(Long recompensaCobrada) {
        this.recompensaCobrada = recompensaCobrada;
    }
}
