package com.daw.onepiece.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tesoreriamarina")
public class TesoreriaEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipooperacion")
    private String tipoOperacion;

    @Column(name = "importe")
    private Long cantidad;

    @Column(name = "fechatransaccion")
    private LocalDateTime fechaOperacion;

    public TesoreriaEntity() {
    }

    public TesoreriaEntity(Integer id, String tipoOperacion, Long cantidad, LocalDateTime fechaOperacion) {
        this.id = id;
        this.tipoOperacion = tipoOperacion;
        this.cantidad = cantidad;
        this.fechaOperacion = fechaOperacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDateTime fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }
}
