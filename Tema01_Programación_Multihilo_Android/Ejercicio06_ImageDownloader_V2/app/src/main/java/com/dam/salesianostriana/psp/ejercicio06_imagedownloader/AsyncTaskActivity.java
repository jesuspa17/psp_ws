package com.dam.salesianostriana.psp.ejercicio06_imagedownloader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskActivity extends AppCompatActivity {

    //atributos
    ImageButton btn_descargar;
    EditText direccion;
    ImageButton descargar;
    ImageView iv;
    ProgressDialog progressDialog;
    URL imageUrl;
    Bitmap imagen;

    int maxProgreso;

    String dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        btn_descargar = (ImageButton) findViewById(R.id.btn_download);
        direccion = (EditText) findViewById(R.id.editUrl);
        iv = (ImageView) findViewById(R.id.img_descargada);

        //obtendrá la url desde el MainActivity
        Intent i = getIntent();
        dir = i.getStringExtra("url");
        direccion.setText(dir);

        btn_descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (direccion.getText().toString().isEmpty())
                    Toast.makeText(AsyncTaskActivity.this, "Introduce URL por favor", Toast.LENGTH_LONG).show();
                else
                    new DescargarImagenTask().execute();
            }
        });
    }

    private class DescargarImagenTask extends AsyncTask<Void, Integer, Void> {

        //Ejecuta el hilo UI.
        //Servirá para inicialiar algún elemento de la UI
        @Override
        protected void onPreExecute() {
            //inicializa el diálogo de progreso
            progressDialog = new ProgressDialog(AsyncTaskActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("Cargando...");
            progressDialog.setProgress(0);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        //Realiza la tarea que se vaya a realizar.
        //En este caso, descargar una imagen desde una url y guardarla como Bitmap.
        @Override
        protected Void doInBackground(Void... params) {
            try {
                //se almacena la url de la imagen en un objeto URL
                imageUrl = new URL(dir);

                //se abre la conexión
                InputStream input = imageUrl.openStream();

                //se obtiene el tamaño del archivo a descargar
                URLConnection conexion = imageUrl.openConnection();
                maxProgreso = conexion.getContentLength();

                //establecemos el tamaño del proceso en función del tamaño de la imagen que se descargará
                //que obtuvimos en la linea de código de arriba
                progressDialog.setMax(maxProgreso);

                for (int i = 1; i <= maxProgreso; i++) {
                    publishProgress(i);
                }
                //guarda en un objeto Bitmap la imagen descargada
                imagen = BitmapFactory.decodeStream(input);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
        //Ejecuta el hilo UI, actualizando la interfaz de usuario.
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        //Recoge el resultado de la tarea.
        @Override
        protected void onPostExecute(Void aVoid) {
            //oculta el progressDialog y muestra la imagen descargada en el ImageView
            progressDialog.dismiss();
            Toast.makeText(AsyncTaskActivity.this, "Tamaño imagen descargada: "
                    + FormatoTamanyo.formatearTamanyo(maxProgreso), Toast.LENGTH_SHORT).show();
            //coloca en el imageView la imagen Bitmap
            iv.setImageBitmap(imagen);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_async_task, menu);
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
