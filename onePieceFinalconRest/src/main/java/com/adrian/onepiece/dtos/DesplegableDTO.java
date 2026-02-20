package com.adrian.onepiece.dtos;

public class DesplegableDTO {

    private Integer id;
    private String nombre;

    public DesplegableDTO() {
    }

    public DesplegableDTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
