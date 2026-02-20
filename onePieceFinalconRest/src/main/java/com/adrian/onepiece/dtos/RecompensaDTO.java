package com.adrian.onepiece.dtos;

public class RecompensaDTO {

    private Integer id;
    private Integer idPirata;
    private String nombrePirata;
    private String tripulacion;
    private Long cantidad;
    private Integer estaVigente;

    public RecompensaDTO() {
    }

    // Constructor para listado con tripulación
    public RecompensaDTO(Integer id, Integer idPirata, String nombrePirata, String tripulacion, Long cantidad,
            Integer estaVigente) {
        this.id = id;
        this.idPirata = idPirata;
        this.nombrePirata = nombrePirata;
        this.tripulacion = tripulacion;
        this.cantidad = cantidad;
        this.estaVigente = estaVigente;
    }

    // Constructor para formularios (sin tripulación)
    public RecompensaDTO(Integer id, Integer idPirata, String nombrePirata, Long cantidad, Integer estaVigente) {
        this.id = id;
        this.idPirata = idPirata;
        this.nombrePirata = nombrePirata;
        this.cantidad = cantidad;
        this.estaVigente = estaVigente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPirata() {
        return idPirata;
    }

    public void setIdPirata(Integer idPirata) {
        this.idPirata = idPirata;
    }

    public String getNombrePirata() {
        return nombrePirata;
    }

    public void setNombrePirata(String nombrePirata) {
        this.nombrePirata = nombrePirata;
    }

    public String getTripulacion() {
        return tripulacion;
    }

    public void setTripulacion(String tripulacion) {
        this.tripulacion = tripulacion;
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
