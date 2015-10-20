package com.dam.salesianostriana.psp.tema01_ejer05_fuerzabruta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 (5) FuerzaBruta: testeo de la fortaleza de una contraseña

Vamos a realizar una aplicación que sea capaz de realizar un ataque por fuerza bruta para conseguir 
adivinar una contraseña. Nos servirá para poder mediar la fortaleza de la contraseña.

Un ataque por fuerza bruta, como su nombre indica, consiste en probar todas la posibilidades 
que existen, hasta dar con la correcta.

Para ello, vamos a desarrollar una aplicación que tenga la siguiente interfaz de usuario:

    -Un EditText, donde se introducirá la contraseña a testear. La cadena introducida 
    habrá que pasarla a mayúsculas (para simplificar el proceso)
    -Un Button, para lanzar el testeo por fuerza bruta.


Para ir generando las cadenas aleatorias, tenemos disponible la clase UtilRandomString, 
con un método llamado getCadenaAlfanumAleatoria, que devuelve un String del número de 
caracteres que le pasemos como argumento.

El proceso de búsqueda hay que lanzarlo mediante un pool de hilos de 1 solo hilo.

Cuando se descubra cual es la contraseña, se debe informar al usuario y parar el proceso de búsqueda. 
Mientras tanto, se debe ir informando al usuario (por el Log) de los intentos realizados 
(como ir avisando de todos sería muy tedioso, lo haremos de 500 en 500 intentos).

Se debe medir el tiempo que ha tardado en realizar el proceso, e imprimirlo por el Log.

NOTA: Si el programa su pusiera a pensar y pensar y no fuera capaz de sacar la contraseña, podemos simplificar el proceso de búsqueda generando cadena solamente de la longitud de la contraseña introducida.
 * */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText pass = (EditText) findViewById(R.id.editPass);
        Button lanzar = (Button) findViewById(R.id.btn_lanzar);

        lanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService ex = Executors.newFixedThreadPool(1);
                ex.execute(new Runnable() {
                    @Override
                    public void run() {
                        boolean encontrado = true;
                        int longitud_cadena = pass.getText().length();
                        while (encontrado) {
                            if (UtilRandomString.getCadenaAlfanumAleatoria(longitud_cadena).equals(pass.getText().toString())) {
                                Log.i("CONTRASEÑA:", "Se ha encontrado la contraseña");
                                encontrado = false;
                            }else{
                                encontrado = true;
                            }
                        }

                    }
                });

            }

        });


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
