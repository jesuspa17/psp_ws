package com.dam.salesianostriana.ejemplo01_sockets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    //Se inicializa el Socket, conectandolo a la IP del servidor y puerto adecuado
                    Socket s = new Socket("172.27.60.7",10000);

                    //Se construye el flujo más adecuado sobre el flujo de salida del socket
                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

                    //Se envia la información
                    printWriter.println("Me he conectado");
                    printWriter.println("Me he conectado");
                    printWriter.println("Me he conectado");
                    printWriter.println("Me he conectado");
                    printWriter.println("FIN");

                    //Se fuerza el envio.
                    printWriter.flush();
                    //Se cierra el socket, que hace que se cierren los flujos
                    //definidos en el.
                    s.close();

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
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
