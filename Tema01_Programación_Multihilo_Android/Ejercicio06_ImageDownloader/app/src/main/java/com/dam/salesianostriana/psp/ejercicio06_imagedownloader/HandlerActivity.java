package com.dam.salesianostriana.psp.ejercicio06_imagedownloader;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class HandlerActivity extends AppCompatActivity {

    //Atributos
    EditText direccion;
    ImageButton descargar;
    ImageView iv;
    ProgressDialog progressDialog;
    URL imageUrl;
    Bitmap imagen;

    int maxProgreso;


    Handler puente = new Handler() {

        //Este es el método que hay que sobreescribir. Aquí dentro
        //escribimos el código que recoge la información del mensaje
        //y la muestra, de manera conveniente, en la interfaz de usuario.
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int progreso = (Integer) msg.obj;
            progressDialog.setProgress(progreso);
            //Si hemos finalizado el progreso, hacemos
            // desaparecer el diálogo
            if (progreso == maxProgreso) {
                progressDialog.dismiss();
                Toast.makeText(HandlerActivity.this, "Tamaño imagen descargada: " + FormatoTamanyo.formatearTamanyo(maxProgreso), Toast.LENGTH_SHORT).show();
                //coloca en el imageView la imagen Bitmap
                iv.setImageBitmap(imagen);
            }

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        //se obtienen los elementos de la UI
        descargar = (ImageButton) findViewById(R.id.btn_okUrl);
        direccion = (EditText) findViewById(R.id.editUrl);
        iv = (ImageView) findViewById(R.id.img_descargada);

        //obtendrá la url desde el MainActivity
        Intent i = getIntent();
        final String dir = i.getStringExtra("url");
        direccion.setText(dir);

        descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //comprueba que existe url en el EditText
                if (direccion.getText().toString().isEmpty())

                    Toast.makeText(HandlerActivity.this, "Introduce URL por favor", Toast.LENGTH_LONG).show();

                else
                    //se inicia la barra de progreso al pulsar el el botón descargar.
                    progressDialog = new ProgressDialog(HandlerActivity.this);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setMessage("Descargando imagen...");
                    progressDialog.setProgress(0);
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
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
                                    int contador = i;
                                    //Creamos la nueva instancia de mensaje
                                    Message m = new Message();
                                    //Le asignamos el contenido
                                    m.obj = contador;
                                    //Enviamos el mensaje
                                    puente.sendMessage(m);
                                }
                                //guarda en un objeto Bitmap la imagen descargada
                                imagen = BitmapFactory.decodeStream(input);


                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }).start();
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