package com.dam.salesianostriana.ejercicio01_serverecho;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText txtMensaje;
    Button btnEnviar;
    Socket s = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtMensaje = (EditText) findViewById(R.id.editTextMensaje);
        btnEnviar = (Button) findViewById(R.id.btn_enviar);

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            //Se inicializa el Socket, conectandolo a la IP del servidor y puerto adecuado
                            s = new Socket("172.27.60.7", 10000);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new recibirMensaje().execute(txtMensaje.getText().toString());

            }
        });
    }

    private class recibirMensaje extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {

            String mensaje = "";

            try {

                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                mensaje = params[0];
                //Se envia la información
                printWriter.println(params[0]);
                //Se fuerza el envio.
                printWriter.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return mensaje;
        }

        @Override
        protected void onPostExecute(String socket) {
            super.onPostExecute(socket);
            txtMensaje.setText("");
            Log.i("MENSAJE_RECIBIDO",socket);
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
