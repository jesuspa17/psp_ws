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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HandlerActivity extends AppCompatActivity {

    EditText direccion;
    Button descargar;
    ImageView iv;
    ProgressDialog progressDialog;

    URL imageUrl = null;
    Bitmap imagen = null;

    Handler puente = new Handler() {

        //Este es el método que hay que sobreescribir. Aquí dentro
        //escribimos el código que recoge la información del mensaje
        //y la muestra, de manera conveniente, en la interfaz de usuario.
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap img = (Bitmap)msg.obj;
            iv.setImageBitmap(img);

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        direccion = (EditText) findViewById(R.id.editUrl);
        descargar = (Button) findViewById(R.id.btn_okUrl);
        direccion = (EditText) findViewById(R.id.editUrl);
        iv = (ImageView) findViewById(R.id.img_descargada);

        Intent i= getIntent();
        final String dir= i.getStringExtra("url");
        direccion.setText(dir);

        descargar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{

                        imageUrl = new URL(dir);

                        InputStream is = imageUrl.openStream();
                        imagen = BitmapFactory.decodeStream(is);

                        Message m = new Message();
                        m.obj = imagen;
                        puente.sendMessage(m);

                    }catch(IOException ex){
                        ex.printStackTrace();
                    }
                }
            }).start();


            /*
            int tam = 0;
            int c;
            while ((c = is.read()) != -1) {
                tam += c;
            }

            //Estas líneas sirven para inicializar el diálogo
            progressDialog = new ProgressDialog(HandlerActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("Cargando...");
            progressDialog.setMax(10000);
            progressDialog.setProgress(0);
            progressDialog.setCancelable(false);
            progressDialog.show();
            */

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