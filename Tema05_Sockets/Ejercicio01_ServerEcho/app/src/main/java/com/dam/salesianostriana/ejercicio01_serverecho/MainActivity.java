package com.dam.salesianostriana.ejercicio01_serverecho;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtMensaje;
    Button btnEnviar;
    ListView listView;
    ArrayList<String> lista_mensajes;

    Socket s = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtMensaje = (EditText) findViewById(R.id.editTextMensaje);
        btnEnviar = (Button) findViewById(R.id.btn_enviar);
        listView = (ListView) findViewById(R.id.listView);


        lista_mensajes = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //Se inicializa el Socket, conectandolo a la IP del servidor y puerto adecuado
                    s = new Socket("192.168.65.2", 10000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = txtMensaje.getText().toString();
                if(msg.trim().isEmpty()){
                    Toast.makeText(MainActivity.this,"Escribe algo killo",Toast.LENGTH_SHORT).show();
                }else{
                    new enviarMensaje().execute(msg);
                    txtMensaje.setText("");
                }

            }
        });
    }

    private class enviarMensaje extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String mensaje = "";

            try {

                //Flujos necesarios.
                BufferedReader bf = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);

                Log.i("ENVIADO", "ENVIADO");
                printWriter.println(params[0]);
                printWriter.flush();

                mensaje = bf.readLine();
                if(mensaje!=null){
                    Log.i("RECIBIDO",mensaje);
                }

                if(params[0].equals("FIN")){
                    Toast.makeText(MainActivity.this,"Se ha finalizado la conexión",Toast.LENGTH_SHORT).show();
                    s.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return mensaje;
        }

        @Override
        protected void onPostExecute(String msg) {
            super.onPostExecute(msg);
            if(msg!=null){
                lista_mensajes.add(msg);
                ArrayAdapter<String> adaptador = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, lista_mensajes);
                listView.setAdapter(adaptador);
            }
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
