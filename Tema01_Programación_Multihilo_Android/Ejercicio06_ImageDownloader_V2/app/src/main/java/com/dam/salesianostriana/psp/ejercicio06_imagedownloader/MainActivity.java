package com.dam.salesianostriana.psp.ejercicio06_imagedownloader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 (6) ImageDownloader

     Vamos a implementar una app que será capaz de descargar imágenes
    desde internet, y que las visualizará en el centro de la pantalla.

     Para poder practicar con la parte específica de Android, vamos a implementar en la misma app
    dos formas diferentes de hacer la misma tarea: una haciendo uso de Handler, y otra haciendo uso de AsyncTask.

     Al acceder a la app, esta debe mostrar un Activity con dos botones:

     Uno de ellos lanzará un nuevo Activity, en el que realizaremos el proceso de descarga y visualización usando Handler.
     El otro, lanzará un Activity diferente, en el que realizaremos el proceso de descarga y visualización a través de un AsyncTask.

     En ambos casos, se deberán cumplir las siguientes especificaciones:

     El layout inclurá un EditText (donde escribir la URL de la imagen), un Button para lanzar
     la descarga y un ImageView centrado en la pantalla, para poder visualizarla.
     Si se pulsa el botón y no hay URL escrita o es errónea, deberá mostrar un Toast de error.
     Durante la descarga de la imagen deberá mostrar un diálogo de progreso, como el utilizado en los ejemplos.
     Al finalizar la ejecución, además de visualizar la imagen en la pantalla, debe mostrar un Toast
     con el número de bytes (en formato humanizado) descargados.

     NOTA: Para descargar un Bitmap (clase mediante la cual vamos a encapsular la imagen) desde internet,
     se puede conjugar el uso del método BitmapFactory.decodeStream(...) y el método openStream() de la clase URL.

 */

public class MainActivity extends AppCompatActivity {
    Button btn_async;
    Button btn_hand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_async = (Button) findViewById(R.id.btn_async);
        btn_hand = (Button) findViewById(R.id.btn_handler);

        //url inicial de ejemplo
        final String url = "https://lavsdeo.files.wordpress.com/2012/06/mc2aastma-delaesperanzamacarena1098.jpg";

        //Incia HandlerActivity
        btn_hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HandlerActivity.class);
                i.putExtra("url",url);
                startActivity(i);
            }
        });

        //Inicia AsyncTaskActivity
        btn_async.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, AsyncTaskActivity.class);
                i.putExtra("url",url);
                startActivity(i);
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
