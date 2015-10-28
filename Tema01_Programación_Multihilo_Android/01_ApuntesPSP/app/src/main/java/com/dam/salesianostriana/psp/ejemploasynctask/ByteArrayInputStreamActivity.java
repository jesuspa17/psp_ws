package com.dam.salesianostriana.psp.ejemploasynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteArrayInputStreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byte_array_input_stream);

        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(openFileInput("hola.txt"));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            final int TAM = 15;
            byte[] buff = new byte[TAM];
            int longitud;

            int tamanyo_leido = 0;

            while ((longitud = bis.read(buff)) > 0) {
                tamanyo_leido += longitud;
                
                baos.write(buff, 0, longitud);

            }
            //se almacena la imagen descargada
            byte[] result = baos.toByteArray();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
