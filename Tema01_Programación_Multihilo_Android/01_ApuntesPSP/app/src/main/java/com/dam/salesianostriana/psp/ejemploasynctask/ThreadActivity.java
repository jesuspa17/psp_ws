package com.dam.salesianostriana.psp.ejemploasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        //Hilo normal

        new Thread(new Runnable() {
            @Override
            public void run() {
                //tarea
            }
        }).start();
    }
}
