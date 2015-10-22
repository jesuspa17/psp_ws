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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
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

    int tamanyo_archivo;



    Handler puente = new Handler() {

        //Este es el método que hay que sobreescribir. Aquí dentro
        //escribimos el código que recoge la información del mensaje
        //y la muestra, de manera conveniente, en la interfaz de usuario.
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            float progreso = (Float) msg.obj;
            progressDialog.setProgress((int) progreso);
            //Si hemos finalizado el progreso, hacemos
            // desaparecer el diálogo
            if (progreso == 100) {
                progressDialog.dismiss();
                Toast.makeText(HandlerActivity.this, "Tamaño imagen descargada: " + FormatoTamanyo.formatearTamanyo(tamanyo_archivo), Toast.LENGTH_LONG).show();
            }

        }

    };
    //Método que recoje la información de la imagen descargada
    Handler imagen_view = new Handler() {

        //Este es el método que hay que sobreescribir. Aquí dentro
        //escribimos el código que recoge la información del mensaje
        //y la muestra, de manera conveniente, en la interfaz de usuario.
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap imagen = (Bitmap) msg.obj;
            iv.setImageBitmap(imagen);

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

                                final int TAM = 10;
                                byte[] buff = new byte[TAM];
                                int longitud;

                                //se almacena la url de la imagen en un objeto URL
                                imageUrl = new URL(dir);

                                //se abre la conexión y se obtiene el tamaño de conexión
                                InputStream input = imageUrl.openStream();
                                URLConnection conexion = imageUrl.openConnection();
                                tamanyo_archivo = conexion.getContentLength();

                                /**Flujos que servirán para descargar la imagen mientras se envia el proceso de descarga
                                en tiempo real
                                 1. Bis = leerá la imagen que se está descargando.
                                 2. Baos = la irá almacenando para después almacenarla en un Bitmap con el método de
                                 BitmapFactory.deCodeByteArray()..
                                 */
                                BufferedInputStream bis = new BufferedInputStream(input);
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                                int tamanyo_leido = 0;

                                while((longitud = bis.read(buff)) > 0) {
                                    tamanyo_leido += longitud;
                                    float porcen = (tamanyo_leido/ (float) tamanyo_archivo)*100;
                                    Message msg = new Message();
                                    msg.obj = porcen;
                                    puente.sendMessage(msg);
                                    baos.write(buff, 0, longitud);

                                }
                                //se almacena la imagen descargada
                                byte[] result = baos.toByteArray();
                                imagen = BitmapFactory.decodeByteArray(result,0, result.length);

                                //se envia el Bitmap de la imagen al Handler
                                Message msg_imagen = new Message();
                                msg_imagen.obj = imagen;
                                imagen_view.sendMessage(msg_imagen);


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