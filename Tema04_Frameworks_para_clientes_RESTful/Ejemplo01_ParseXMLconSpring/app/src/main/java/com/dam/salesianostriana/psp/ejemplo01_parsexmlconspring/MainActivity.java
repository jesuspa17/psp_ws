package com.dam.salesianostriana.psp.ejemplo01_parsexmlconspring;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.textView);

        new GetDataXMLTask().execute();


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

    private class GetDataXMLTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params) {

            /**

             Esto se debe meter en el gradle, abajo del release. Sino, peta

             packagingOptions{
                exclude 'META-INF/notice.txt'
                exclude 'META-INF/license.txt'
             }
             */


            String url = "https://ajax.googleapis.com/ajax/" +
                    "services/search/web?v=1.0&q={query}";

            RestTemplate restTemplate = new RestTemplate();

            //El tipo de mensaje también influye;
            //aquí hay una lista de los mensajes q se le pueden pasar dependiendo del tipo de dato que queremos obtener (xml, json, etc...)
            //http://docs.spring.io/autorepo/docs/spring-android/1.0.x/reference/html/rest-template.html
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            //Android es el parámetro que se le pasa en el "query" de la url.
            //En este caso se le pasa la clase String, Si le pasamos una pojo, encapsulará
            //los datos en la clase correspondiente.

            String result = restTemplate.getForObject(url, String.class, "Android");

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }
}
