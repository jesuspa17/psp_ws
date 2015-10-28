package com.dam.salesianostriana.psp.ejemploasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executor);

        final ExecutorService executorService = Executors.newFixedThreadPool(1);

        //IMPLEMENTADO CON UN CALLABLE Y RECOGIENDO DATOS CON FUTURE

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                LongitudPalabra longitudPalabra = new LongitudPalabra("HOLA");

                Future<Integer> fut = executorService.submit(longitudPalabra);

                try {

                    Integer valor = fut.get();
                    Log.i("VALOR RECOGIDO: ", String.valueOf(valor));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });

        executorService.shutdown();

    }
}
