package com.jesusps.psp_ejer1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
/**
 (1) Números pares e impares

Implementar una app que haciendo uso de dos hilos de ejecución secundarios (diferentes al hilo principal) que realicen las siguientes tareas:

    Hilo 1: imprimir por el log los 100 primeros números pares. 
    Al finalizar la impresión de números, debe aparecer el mensaje FINAL DEL HILO 1.
    Hilo 2: imprimir por el log los 100 primeros números impares. 
    Al finalizar la impresión de números, debe aparecer el mensaje FINAL DEL HILO 2.
    
 * */

public class MainActivity extends Activity {

    public void esperarTurno(int segundos){
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i<=100; i++){
                    if(i%2==0){
                        System.out.println(i);
                    }else{
                    }
                }
                System.out.println("FINAL HILO 1");

            }

        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i<=100; i++){
                    if(i%2!=0){
                        System.out.println(i);
                    }else{
                    }
                }
                System.out.println("FINAL HILO 2");
            }

        }).start();


        for(int i=0; i<4;i++){
            for(int j=0;j<=4;j++){
                System.out.print("+");
                System.out.print("");
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
