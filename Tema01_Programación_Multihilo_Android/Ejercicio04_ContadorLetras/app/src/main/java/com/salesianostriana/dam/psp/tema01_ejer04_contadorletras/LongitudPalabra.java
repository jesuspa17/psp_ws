package com.salesianostriana.dam.psp.tema01_ejer04_contadorletras;

import java.util.concurrent.Callable;

/**
 * @author Jesús Pallares
 */
public class LongitudPalabra implements Callable<Integer>{

    private String texto;

    public LongitudPalabra(String _texto){
        this.texto = _texto;
    }

    @Override
    public Integer call() throws Exception {

        return Integer.valueOf(texto.length());
    }
}
