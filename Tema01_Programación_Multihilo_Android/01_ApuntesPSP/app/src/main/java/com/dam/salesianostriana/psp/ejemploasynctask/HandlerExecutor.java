package com.dam.salesianostriana.psp.ejemploasynctask;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandlerExecutor extends AppCompatActivity {

    ProgressDialog progressDialog;


    Handler puente = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Integer proceso = (Integer) msg.obj;
            progressDialog.setProgress(proceso);
            if(proceso == 255){
                progressDialog.dismiss();
                Toast.makeText(HandlerExecutor.this, "Proceso terminado", Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(HandlerExecutor.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.setMax(255);
        progressDialog.show();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int contador;

                for(int i = 0; i < 256; i++){

                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    contador = i;

                    Message proceso = new Message();
                    proceso.obj = contador;
                    puente.sendMessage(proceso);

                }

            }
        });

        executorService.shutdown();


    }





}
