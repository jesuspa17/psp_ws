package com.salesianostriana.dam.psp.tema01_ejer04_contadorletras;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Jesús Pallares
 */
 
 /**
  (4) Contador de letras por palabra

Implementar una aplicación que realice varios cáclulos sobre un texto que se le proporcione, 
y cuya interfaz tenga los siguientes elementos:

    -Un EditText (de varias líneas) en el que podremos escribir un texto. 
    Para facilitar la corrección del ejercicio, este traerá precargado, por defecto, el primer 
    párrafo de El Quijo (ver aquí).
    -Un Button, que cuando sea pulsado comenzará el proceso de cálculo.
    -Un Button, que reseteará el texto del EditText.
    -Un TextView, donde mostrará los resultados del cálculo.

Los cálculos que deberá realizar la app, u cuyos resultados debe mostrar son:

    -Número de palabras que tiene el texto (el método lo definirá el/la alumno/a).
    -Suma del número de letras que tienen las palabras (sin contar espacios, 
    símbolos o signos de puntuación). Para calcularlo, tendrá que hacer uso de una clase 
    que implemente Callable<Integer>, que recibirá en el constructor una palabra, 
    y que devolverá el número de letras de esa palabra. Para ejecutar los Callable, se hará uso de un 
    Thread Pool de 3 hilos. Hay que tener en cuenta que para recoger los diferentes Future<Integer> 
    que se generarán, habrá que usar una colección.
    -Número medio de letras por palabra.

  * */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Variables

    private EditText editTextTexto;
    private Button btn_go;
    private Button btn_reset;
    private TextView textViewNumPalabras;
    private TextView textViewTotalLetras;
    private TextView media;


    /**
     * Calcula el número de palabras que contiene una cadena.
     * @param cadena
     * @return
     */
    public int calcularNumPalabras(String cadena){
        StringTokenizer st = new StringTokenizer(cadena);
        return st.countTokens();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Rescatamos los elementos de la UI para poder tratarlos en esta clase.
        editTextTexto = (EditText) findViewById(R.id.editTextTexto);
        btn_go = (Button) findViewById(R.id.btn_go);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        textViewNumPalabras = (TextView) findViewById(R.id.textViewNumPalabras);
        textViewNumPalabras = (TextView) findViewById(R.id.textViewNumPalabras);
        textViewTotalLetras = (TextView) findViewById(R.id.textViewTotalLetras);
        media = (TextView) findViewById(R.id.textViewMedia);

        //Asociamos evento al botón "GO"
        btn_go.setOnClickListener(this);

        //Asociamos evento al botón "RESET"
        btn_reset.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_go:

                //Almacenamos en un String el texto que haya en el EditTextTexto de la UI
                String cadena = editTextTexto.getText().toString();

                //Calculamos el número de palabras existentes en la cadena y lo mostramos por el UI
                final int num_letras = calcularNumPalabras(editTextTexto.getText().toString());
                textViewNumPalabras.setText(String.valueOf(num_letras));

                //Iniciamos un ExecutorService de 3 Hilos
                ExecutorService ex = Executors.newFixedThreadPool(3);

                //Colección donde guardaremos los datos extraidos por el Future
                ArrayList<Integer> datos =new ArrayList<Integer>();

                //Convierte "cadena", que es la cadena obtenida a través de la UI, en un array
                //a través de del método split(), que sólo cogerá palabras omitiendo espacios en
                //blanco y signos
                String [] arrayPalabras = cadena.split("[^a-zA-Z0-9]");

                //almacenará la suma del número de letras que tiene cada palabra
                int suma = 0;

                //recorrerá el array anterior
                for(int i = 0; i < arrayPalabras.length; i++){

                    //A través de la clase implementada por nosotros vamos a obtener la longitud
                    //de la cadena formateada anteriormente
                    LongitudPalabra callable = new LongitudPalabra(arrayPalabras[i]);

                    //Ejecutamos el Callable
                    Future<Integer> f = ex.submit(callable);

                    Integer dato = null;
                    try {
                        //obtenemos dato y lo almacenamos en el array para posteriormente sumarlo
                        dato = f.get();
                        datos.add(dato);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }

                //Recorremos el array y sumamos los elementos
                for(Integer ml : datos){
                    suma = suma + ml;
                }

                //Mostramos la suma por el UI
                textViewTotalLetras.setText(String.valueOf(suma));

                //Realizamos la media
                int media_total = 0;
                media_total = suma / num_letras;

                //mostramos por el UI
                media.setText(String.valueOf(media_total));

                break;

            case R.id.btn_reset:
                //vacía todos los campos
                textViewNumPalabras.setText("");
                textViewTotalLetras.setText("");
                editTextTexto.setText("");
                media.setText("");

                break;
        }


    }
}
