package com.dam.salesianostriana.psp.ejemploasynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedWriterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffered_writer);

        BufferedReader bin = null;

        try
        {
             bin = new BufferedReader(new InputStreamReader(openFileInput("prueba_int.txt")));
            String texto = bin.readLine();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(bin != null)
                bin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
