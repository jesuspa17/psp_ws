package com.dam.salesianostriana.psp.ejemploasynctask;

import java.util.concurrent.Callable;

/**
 * Created by Jesus on 28/10/2015.
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
