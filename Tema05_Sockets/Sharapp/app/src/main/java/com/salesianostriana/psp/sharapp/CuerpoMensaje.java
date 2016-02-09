package com.salesianostriana.psp.sharapp;

/**
 * Created by Jesus Pallares on 09/02/2016.
 */
public class CuerpoMensaje {

    private String nombre;
    private String fecha;

    public CuerpoMensaje(){}

    public CuerpoMensaje(String nombre, String fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
