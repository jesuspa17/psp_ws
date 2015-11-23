package com.dam.salesianostriana.psp.volleyfirstsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    /**
     * Elementos que necesitaremos para la RequestQueue
     *
     *        |string request
     * Request|image request
     *        |JSONObjectRequest
     *        |JSONArrayRequest
     *
     * Response.Listener<~>
     *
     * Response.ErrorListener
     *
     *              | GSON
     * CustomRequest|
     *              | SIMPLEXML
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final TextView textView = (TextView) findViewById(R.id.textView);

        //Se crea el objeto RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

        //Dependiendo del objeto que necesitaremos se creara una Request u otra
        //En este caso estamos extrayendo  una cadena String
        //Pero en otros casos podríamos hacer un JSONRequest...

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Obtendrá los primero 500 carácteres del código fuente de la página de Google.
                        textView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Esto no funciona!");
            }
        });

        queue.add(stringRequest);

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
