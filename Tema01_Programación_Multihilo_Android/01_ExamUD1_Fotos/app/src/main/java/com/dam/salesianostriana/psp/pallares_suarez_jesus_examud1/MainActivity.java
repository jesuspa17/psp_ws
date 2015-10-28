package com.dam.salesianostriana.psp.pallares_suarez_jesus_examud1;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 @author Jesús Pallares.

 Examen PSP UD1. Programación multihilo en android.

 */

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button descargar;
    Button rotar;
    ProgressDialog progressDialog;
    Bitmap bitmap;


    /**     HANDLER   **/
    Handler puente = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //recibe el mensaje
            bitmap = (Bitmap) msg.obj;
            imageView.setImageBitmap(bitmap);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        descargar = (Button) findViewById(R.id.btn_descargar);
        rotar = (Button) findViewById(R.id.btn_rotar);

        descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImageTask().execute();
            }
        });
        rotar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        //se envia el mensaje al Handler.
                        msg.obj = ImageUtils.rotate90rigth(bitmap);
                        puente.sendMessage(msg);
                    }
                }).start();


            }
        });
    }

    /**         ASYNCTASK          **/
    private class ImageTask extends AsyncTask<Void, Bitmap, Bitmap>{

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Cargando imagen...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {

                String imagenDescargada = ImageUtils.getRandomPhoto();
                URL url = new URL(imagenDescargada);
                InputStream is =  url.openStream();

                bitmap = BitmapFactory.decodeStream(is);
                publishProgress(bitmap);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Bitmap... values) {
            progressDialog.dismiss();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
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
