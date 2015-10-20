package com.salesianostriana.psp.tema02_ejerciciocarrera;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
(2) Carrera olímpica

En este ejercicio vamos a realizar un experimento: si lanzamos 8 hilos que realizan la misma tarea, 
y el planificador le asigna el mismo tiempo de ejecución a cada uno, debería finalizar primero el primer 
hilo que se lanzó, y así sucesivamente, ¿no?. Lo comprobaremos de la siguiente forma: supongamos 
que tendremos una carrera del gran premio de atletismo (la de 1500 metros).

Tendremos que crear la clase Corredor, que tendrá que implementar un bucle que cuente desde 0 a 1500 
(una iteración por metro recorrido). Cuando finalice el bucle, deberá mostrar un mensaje 
por pantalla indicando el nombre del corredor y que ha finalizado (El corredor Fermín Cacho ha finalizado la prueba).

NOTA: Si los corredores realizan la carrera muy rápido, se puede introducir, dentro del bucle, 
un pequeño lapso de tiempo (10 ms) de sueño, mediante el uso del método sleep.

NOTA 2: Para poder proporcionarle a cada corredor un nombre, podemos añadir a la 
clase Corredor un constructor que reciba dicho nombre como parámetro.

 * */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] corredores = { "Florentio Pérez", "Joan Laporta",
                "Lopera", "Del Nido", "Jesús Gil",
                "Usain Bolt", "Cristano Ronaldo", "Messi" };

        for (int i = 0; i < corredores.length; i++) {
            new Thread(new Corredor(corredores[i])).start();
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
