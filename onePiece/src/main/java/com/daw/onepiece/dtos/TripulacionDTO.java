package com.daw.onepiece.dtos;

public class TripulacionDTO {

    private int id;
    private String nombre;
    private String barco;
    private int estaActiva;
    private int cantidadReclutados;

    public TripulacionDTO(int id, String nombre, String barco, int estaActiva, int cantidadReclutados) {
        this.id = id;
        this.nombre = nombre;
        this.barco = barco;
        this.estaActiva = estaActiva;
        this.cantidadReclutados = cantidadReclutados;
    }

    public TripulacionDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(int estaActiva) {
        this.estaActiva = estaActiva;
    }

    public int getCantidadReclutados() {
        return cantidadReclutados;
    }

    public void setCantidadReclutados(int cantidadReclutados) {
        this.cantidadReclutados = cantidadReclutados;
    }
    
    public int getNumeroMiembros() {
        return cantidadReclutados;  // alias para que el HTML funcione sin cambiarlo
    }
}