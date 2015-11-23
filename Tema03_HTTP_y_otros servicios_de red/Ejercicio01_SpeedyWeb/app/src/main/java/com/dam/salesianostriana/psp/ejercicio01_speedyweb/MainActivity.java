package com.dam.salesianostriana.psp.ejercicio01_speedyweb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 (1) SpeedyWe

 Vamos a implementar nuestro propio navegador de internet.
 Para ello, definiremos una interfaz de usuario que tendrá,
 en la parte superior, un EditText (en el que escribiremos las urls) y
 un Button (que servirá para cargar las páginas). La parte inferior tendrá un
 WebView. La carga de las páginas la haremos a través de Volley, descargándonos
 el HTML de la página indicada por la URL, y cargándola en el WebView a través del método loadDataWithBaseUrl(...).
 */

public class MainActivity extends AppCompatActivity {

    EditText direccion;
    Button buscar;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        direccion = (EditText) findViewById(R.id.editText);
        buscar = (Button) findViewById(R.id.btn_buscar);
        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

        //evita que los enlaces se abran fuera nuestra app
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

        });

        buscar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String url = direccion.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        webView.loadDataWithBaseURL(null,response,"text/html","utf-8",null);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error 404.", Toast.LENGTH_SHORT);
                    }
                });
                VolleyApplication.requestQueue.add(stringRequest);
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
