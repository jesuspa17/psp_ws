package com.dam.salesianostriana.psp.ejemploasynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AsynctaskActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        new MiTask().execute();
    }


    private class MiTask extends AsyncTask<Void, Integer, Void>{


        @Override
        protected void onPreExecute() {

            //se inicializan elementos de la UI
            progressDialog = new ProgressDialog(AsynctaskActivity.this);
            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);
            progressDialog.setMax(256);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            for(int i = 0; i < 256; i++){

                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //SE PUBLICA EL PROCESO. Siempre desde este método.
                publishProgress(i);
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
            //actualiza los elementos de la UI

        }

        @Override
        protected void onPostExecute(Void aVoid) {
           //resulado de la tarea
           progressDialog.dismiss();

        }
    }
}
