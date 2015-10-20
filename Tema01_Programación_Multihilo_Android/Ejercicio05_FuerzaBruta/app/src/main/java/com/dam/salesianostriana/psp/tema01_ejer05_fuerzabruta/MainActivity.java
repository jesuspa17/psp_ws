package com.dam.salesianostriana.psp.tema01_ejer05_fuerzabruta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText pass = (EditText) findViewById(R.id.editPass);
        Button lanzar = (Button) findViewById(R.id.btn_lanzar);

        lanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService ex = Executors.newFixedThreadPool(1);
                ex.execute(new Runnable() {
                    @Override
                    public void run() {
                        boolean encontrado = true;
                        int longitud_cadena = pass.getText().length();
                        while (encontrado) {
                            if (UtilRandomString.getCadenaAlfanumAleatoria(longitud_cadena).equals(pass.getText().toString())) {
                                Log.i("CONTRASEÑA:", "Se ha encontrado la contraseña");
                                encontrado = false;
                            }else{
                                encontrado = true;
                            }
                        }

                    }
                });

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
