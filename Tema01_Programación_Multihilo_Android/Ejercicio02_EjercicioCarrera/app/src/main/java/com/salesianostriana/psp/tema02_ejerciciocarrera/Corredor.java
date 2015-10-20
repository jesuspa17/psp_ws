package com.salesianostriana.psp.tema02_ejerciciocarrera;

import java.util.Random;

/**
 * Created by Jesus on 03/10/2015.
 */
public class Corredor implements Runnable{

    private String nombre;

    public Corredor (String nombre_){
        this.nombre=nombre_;
    }

    @Override
    public void run() {
        Random r = new Random();
        for (int i = 0; i < 1500; i++) {
            try {
                Thread.sleep(r.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(nombre+" ha terminado la carrera.");

    }
}
