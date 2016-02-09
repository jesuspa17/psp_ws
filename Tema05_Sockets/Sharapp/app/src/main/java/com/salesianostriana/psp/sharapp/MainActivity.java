package com.salesianostriana.psp.sharapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nononsenseapps.filepicker.FilePickerActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private static int FILE_CODE = 1;

    List<CuerpoMensaje> lista;

    Socket s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        lista = new ArrayList<>();
        lista.add(new CuerpoMensaje("Enrique", "29/09/16"));
        lista.add(new CuerpoMensaje("Pablo harta kilos","23/05/16"));
        lista.add(new CuerpoMensaje("Pona","Hijo puta"));
        lista.add(new CuerpoMensaje("Jesús","Pallares"));

        adapter = new MensajeAdapter(lista);
        recyclerView.setAdapter(adapter);

        new Conexion().execute("jesuspa");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent i = new Intent(MainActivity.this, FilePickerActivity.class);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
                i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
                i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
                startActivityForResult(i, FILE_CODE);


            }
        });
    }


    /**
     * Este el método que recogerá el archivo que hemos seleccionado mediante el FilePicker.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == FILE_CODE && resultCode == Activity.RESULT_OK){
           if (data.getBooleanExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false)) {
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                   ClipData clip = data.getClipData();
                   if (clip != null) {
                       for (int i = 0; i < clip.getItemCount(); i++) {

                           Uri uri = clip.getItemAt(i).getUri();
                           Toast.makeText(this, "Enviando fichero..." + uri.getPath(), Toast.LENGTH_SHORT).show();

                           //Aquí tendré que instanciar el mecanismo de envio de imágenes.
                           //new SendFileTask().execute(uri.getPath());
                           new EnviarMensaje().execute(uri.getPath());
                       }
                   }

                   //Para otras versiones de Android más antiguas.
               }else{
                   ArrayList<String> paths = data.getStringArrayListExtra
                           (FilePickerActivity.EXTRA_PATHS);

                   if (paths != null) {
                       for (String path: paths) {

                           Uri uri = Uri.parse(path);
                           Toast.makeText(this, "Enviando fichero..." + uri.getPath(), Toast.LENGTH_SHORT).show();

                           //Aquí tendré que instanciar el mecanismo de envio de imágenes.
                           //new SendFileTask().execute(uri.getPath());
                           new EnviarMensaje().execute(uri.getPath());
                       }
                   }
               }

           }else {
               Uri uri = data.getData();
               Toast.makeText(this, "Enviado fichero..." + uri.getPath(), Toast.LENGTH_SHORT).show();

               //Aquí tendré que instanciar el mecanismo de envio de imágenes.
               //new SendFileTask().execute(uri.getPath());
               new EnviarMensaje().execute(uri.getPath());
           }
       }
    }

    class EnviarMensaje extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {

            int SIZE = 64 * 1024;

            try {

                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(params[0]));
                ByteArrayOutputStream baos = new ByteArrayOutputStream(SIZE);
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());


                byte[] buffer = new byte[SIZE];

                int c;

                while((c = bis.read(buffer,0,SIZE)) != -1){
                    baos.write(buffer,0,c);
                }


                SharappMessage sharappMessage = new SharappMessage();
                sharappMessage.typeMessage = 2;
                sharappMessage.fileName = params[0].split("/")[params[0].split("/").length-1];
                sharappMessage.content = baos.toByteArray();
                sharappMessage.userName = "jesus";
                sharappMessage.date = new Date();
                sharappMessage.message = "hello";

                oos.writeObject(sharappMessage);

                baos.close();
                bis.close();
                oos.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


    class Conexion extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            if(params!=null){

                //192.168.65.2
                try {

                    s = new Socket("192.168.65.2", 10000);

                    //flujo de escritura.
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(params[0]));

                    //flujo que envia el objeto a través del socket
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

                    //Se crea el objeto a enviar.
                    SharappMessage sharappMessage = new SharappMessage();
                    sharappMessage.typeMessage = 1;
                    sharappMessage.userName = "jesuspa";

                    oos.writeObject(sharappMessage);

                    bis.close();
                    oos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
