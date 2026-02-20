package com.daw.onepiece.dtos;

import java.time.LocalDateTime;

public class TesoreriaDTO {

    private Integer id;
    private String tipoOperacion;
    private Long cantidad;
    private LocalDateTime fechaOperacion;

    public TesoreriaDTO() {
    }

    public TesoreriaDTO(Integer id, String tipoOperacion, Long cantidad, LocalDateTime fechaOperacion) {
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
