package com.dam.salesianostriana.psp.ejemplo01_retrofitfirstsample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* [------------------> PASOS RetroFit <----------------------]
           [------------------> PASOS RetroFit <----------------------]

            [0] Conocer el servicio del que se va a consumir.

            [1] Definimos en una interface el esqueleto del servicio
                para cada petición:

                    -verbo HTTP (Get, post, update, delete)
                    -ruta (parcial) del recurso
                    -tipo de retorno

               Quedaría tal que asi:

                    public interface GitHubService {

                        @GET("/users/{user}/repos")
                        Call<List<Repo>> listRepos(@Path("user") String user);
                    }

            [2] Contruimos un objeto Retrofit con los parámetros necesarios:

                Esto será capaz de montar el servicio en función de la interface que hemos
                creado anteriormente.

                   Retrofit retrofit = new Retrofit.Builder()
                                       .baseUrl("https://api.github.com")
                                       .build();

             [3] Construimos el servicio:

                    GitHubService service = retrofit.create(GitHubService.class);

             [4] Realiza la petición:

                    Call<List<Repo>> repos = service.listRepos("octocat");


             [------------------> PASOS RetroFit <----------------------]
             [------------------> PASOS RetroFit <----------------------]

         */




        new GetDataTask().execute();

    }



    private class GetDataTask extends AsyncTask<Void, Void, List<GitHub>> {

        @Override
        protected List<GitHub> doInBackground(Void... params) {

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl("https://api.github.com")
                    .build();

            GitHubService service = retrofit.create(GitHubService.class);

            Call<List<GitHub>> repos = service.listRepos("octocat");
            Response<List<GitHub>> result = null;

            try {
                result = repos.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (result != null) {
                return result.body();
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<GitHub> gitHubs) {
            super.onPostExecute(gitHubs);
                Log.i("RESULT",gitHubs.toString());

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
