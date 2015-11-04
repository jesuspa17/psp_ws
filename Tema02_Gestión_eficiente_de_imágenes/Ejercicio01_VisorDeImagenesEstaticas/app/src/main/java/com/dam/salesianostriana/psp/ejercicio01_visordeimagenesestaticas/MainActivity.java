package com.dam.salesianostriana.psp.ejercicio01_visordeimagenesestaticas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * (1) Visor de imágenes de 10 fotos estáticas (VintageToyCamApp)

 Con esta app vamos a simular una cámara de fotos de juguete antigua. Como este juguete,
 te acercabas el visor de la cámara de fotos a los ojos, podías ver fotos de monumentos,
 como la Giralda, la Torre del Oro, la Alhambra, ...



 Se deben cumplir las siguientes especificaciones:

 Los ficheros se proporcionan en esta entrega. Hay que incluirlos como recursos del proyecto, y cargarlos desde ahí.
 Las fotos se visualizarán siempre en un orden determinado. Sin embargo, la primera foto que se visualizará al
 cargar la app tiene que ser una posición aleatoria de la colección de fotos.
 Si se pulsa el botón Siguiente, se cargará en el ImageView la siguiente fotografía.
 Si se pulsa el botón Anterior, se cargará en el ImageView la imagen anterior.
 Si se pulsa el botón Siguiente cuando estemos visualizando la última fotografía, debemos pasar de nuevo a la primera.
 Si se pulsa el botón Anterior cuando estamos visualizando la primera fotografía, debemos pasar de nuevo a la última.


 */
public class MainActivity extends AppCompatActivity {

    ImageView imagen;
    Button anterior,siguiente;

    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = (ImageView) findViewById(R.id.imageView);
        anterior = (Button) findViewById(R.id.btn_anterior);
        siguiente = (Button)findViewById(R.id.btn_siguiente);

        final ArrayList<Integer> fotos = new ArrayList<Integer>();
        fotos.add(R.drawable.alcazaba_almeria);
        fotos.add(R.drawable.alcazaba_malaga);
        fotos.add(R.drawable.alhambra);
        fotos.add(R.drawable.catedral_jaen);
        fotos.add(R.drawable.giralda);
        fotos.add(R.drawable.mezquita_de_cordoba);
        fotos.add(R.drawable.monasterio_rabida);
        fotos.add(R.drawable.salvador_ubeda);
        fotos.add(R.drawable.torre_del_oro);

        contador = (int) (Math.random()*9);
        cargarImagen(fotos);

        Log.i("NÚMERO", "TAMAÑO ARRAY: " + fotos.size());
        Log.i("NÚMERO", "Num: " + contador);


        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador++;
                if (contador > (fotos.size() - 1)) {
                    contador = 0;
                }
                cargarImagen(fotos);
                Log.i("NÚMERO", "Num: " + contador);

            }
        });


        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador--;
                if(contador < 0) {
                contador = fotos.size() - 1;
                }
                cargarImagen(fotos);
                Log.i("NÚMERO", "Num: " + contador);


            }
        });

    }

    public void cargarImagen(ArrayList<Integer> fotos){

        Picasso.with(MainActivity.this)
                .load(fotos.get(contador))
                .resize(500,350)
                .into(imagen);
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
