package com.dam.salesianostriana.psp.ejercicio06_imagedownloader;

import java.text.DecimalFormat;

/**
 * Created by Jesus on 22/10/2015.
 */
public class FormatoTamanyo {

    public static String formatearTamanyo(int tam) {
        String tam_formateado = "";
        double kb = tam / 1024;
        double mb = tam / 1048576;
        double gb = tam / 1073741824;
        DecimalFormat dec = new DecimalFormat("0.00");

        if (kb > 0) {
            tam_formateado = dec.format(kb).concat("KB");
        }
        if (mb > 0) {
            tam_formateado = dec.format(mb).concat("MB");
        }
        if (gb > 0) {
            tam_formateado = dec.format(gb).concat("GB");
        }
        return tam_formateado;
    }
}
