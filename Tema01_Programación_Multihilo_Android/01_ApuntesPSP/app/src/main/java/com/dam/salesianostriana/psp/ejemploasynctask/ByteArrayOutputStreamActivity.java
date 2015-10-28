package com.dam.salesianostriana.psp.ejemploasynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteArrayOutputStreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byte_array_output_stream);


        BufferedInputStream in = null;
        try {

            in = new BufferedInputStream(openFileInput("hola.txt"));
            ByteArrayOutputStream bout = new ByteArrayOutputStream();

            final int TAM = 32 * 1024;

            byte[] buff = new byte[TAM];
            int len;

            while ((len = in.read(buff)) > 0) {
                bout.write(buff, 0, len);

            }
            
            byte[] result = bout.toByteArray();


            in.close();
            bout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
