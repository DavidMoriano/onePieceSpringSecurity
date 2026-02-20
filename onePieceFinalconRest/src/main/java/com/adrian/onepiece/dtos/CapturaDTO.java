package com.adrian.onepiece.dtos;

public class CapturaDTO {

    private Integer id;
    private Integer idPirata;
    private String nombrePirata;
    private String lugarCaptura;
    private Long recompensaCobrada;

    public CapturaDTO() {
    }

    // Constructor para listado sin tripulación
    public CapturaDTO(Integer id, Integer idPirata, String nombrePirata, String lugarCaptura,
            Long recompensaCobrada) {
        this.id = id;
        this.idPirata = idPirata;
        this.nombrePirata = nombrePirata;
        this.lugarCaptura = lugarCaptura;
        this.recompensaCobrada = recompensaCobrada;
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
