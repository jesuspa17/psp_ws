package com.dam.salesianostriana.psp.ejemploasynctask;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BufferedReaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffered_reader);

        BufferedWriter bout = null;

        try {

            bout = new BufferedWriter(new OutputStreamWriter(openFileOutput("prueba.txt", Context.MODE_PRIVATE)));
            bout.write("TEXTO");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(bout != null)
                    bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
