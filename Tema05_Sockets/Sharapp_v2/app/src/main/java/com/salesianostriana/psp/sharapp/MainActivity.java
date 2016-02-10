package com.salesianostriana.psp.sharapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private static int FILE_CODE = 1;

    private static int CODIGO_CONEXION = 1;
    private static int CODIGO_ENVIO = 2;
    private static int CODIGO_DESCONEXION = 3;

    List<CuerpoMensaje> lista;
    String nom_user;

    Socket s;
    //Flujos necesarios.
    ObjectOutputStream oos;
    ObjectInputStream ois;
    BufferedInputStream bis;
    ByteArrayOutputStream baos;

     Handler puente = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            SharappMessage sharappMessage = (SharappMessage) msg.obj;

            String username = sharappMessage.userName;
            Date fecha = sharappMessage.date;
            Log.i("HANDLER_USERNAME", sharappMessage.userName);
            Log.i("HANDLER_DATE", sharappMessage.date.toString());

            Utils.saveImage(MainActivity.this,sharappMessage.fileName,sharappMessage.content);
            Bitmap b = Utils.decodeBitmapSize(sharappMessage.content, 300);

            lista.add(new CuerpoMensaje(username, fecha,b));
            adapter = new MensajeAdapter(lista);
            recyclerView.setAdapter(adapter);

        }
    };

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
        Utils.initializePreferences(this);

        lista = new ArrayList<>();

        adapter = new MensajeAdapter(lista);
        recyclerView.setAdapter(adapter);

        nom_user = Utils.preferences.getString("nom_user", null);
        new Conexion().execute(nom_user);


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


    //No la uso
    class GetMessages implements Runnable {

        @Override
        public void run() {
            try {

                    while (true){
                        Object objRecibido = ois.readObject();
                        if (objRecibido instanceof SharappMessage) {
                            Message msg = new Message();
                            SharappMessage obj = (SharappMessage) objRecibido;
                            msg.obj = obj;
                            puente.sendMessage(msg);
                        } else {

                        }
                    }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }


        /**
         * Este el método que recogerá el archivo que hemos seleccionado mediante el FilePicker.
         *
         * @param requestCode
         * @param resultCode
         * @param data
         */
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == FILE_CODE && resultCode == Activity.RESULT_OK) {
                if (data.getBooleanExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        ClipData clip = data.getClipData();
                        if (clip != null) {
                            for (int i = 0; i < clip.getItemCount(); i++) {

                                Uri uri = clip.getItemAt(i).getUri();
                                Toast.makeText(this, "Enviando fichero..." + uri.getPath(), Toast.LENGTH_SHORT).show();
                                new EnviarMensaje().execute(nom_user, uri.getPath());
                            }
                        }

                        //Para otras versiones de Android más antiguas.
                    } else {
                        ArrayList<String> paths = data.getStringArrayListExtra
                                (FilePickerActivity.EXTRA_PATHS);

                        if (paths != null) {
                            for (String path : paths) {

                                Uri uri = Uri.parse(path);
                                Toast.makeText(this, "Enviando fichero..." + uri.getPath(), Toast.LENGTH_SHORT).show();
                                new EnviarMensaje().execute(nom_user, uri.getPath());
                            }
                        }
                    }

                } else {
                    Uri uri = data.getData();
                    Toast.makeText(this, "Enviado fichero..." + uri.getPath(), Toast.LENGTH_SHORT).show();
                    new EnviarMensaje().execute(nom_user, uri.getPath());
                }
            }
        }

        private class Conexion extends AsyncTask<String, Void, Void> {

            @Override
            protected Void doInBackground(String... params) {
                if (params != null) {


                    try {
                        //192.168.65.2
                        //172.27.60.7
                        s = new Socket("192.168.65.2", 10000);

                        //flujo que envia el objeto a través del socket
                        oos = new ObjectOutputStream(s.getOutputStream());
                        ois = new ObjectInputStream(s.getInputStream());

                        //Se crea el objeto a enviar.
                        SharappMessage sharappMessage = new SharappMessage();
                        sharappMessage.typeMessage = CODIGO_CONEXION;
                        sharappMessage.userName = params[0];

                        oos.writeObject(sharappMessage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                ExecutorService executorService = Executors.newFixedThreadPool(3);
                executorService.execute(new RecibirMensaje(ois,puente));
            }
        }

        private class EnviarMensaje extends AsyncTask<String, Void, Void> {

            @Override
            protected Void doInBackground(String... params) {



                if (params != null) {

                    String nomRecibido = params[0];
                    String rutaImagen = params[1];

                    try {
                        int TAM = 64 * 1024;
                        bis = new BufferedInputStream(new FileInputStream(rutaImagen));
                        baos = new ByteArrayOutputStream();

                        byte[] buffer = new byte[TAM];
                        int c;
                        while ((c = bis.read(buffer, 0, TAM)) != -1) {
                            baos.write(buffer, 0, c);
                        }

                        SharappMessage sharappMessage = new SharappMessage();
                        sharappMessage.typeMessage = CODIGO_ENVIO;
                        sharappMessage.userName = nomRecibido;
                        sharappMessage.fileName = rutaImagen.split("/")[rutaImagen.split("/").length - 1];
                        sharappMessage.content = baos.toByteArray();
                        sharappMessage.message = "Hola";
                        sharappMessage.date = Calendar.getInstance().getTime();

                        oos.writeObject(sharappMessage);

                        bis.close();
                        baos.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }

        private class Desconexion extends AsyncTask<String, Void, Void> {

            @Override
            protected Void doInBackground(String... params) {

                if (params != null) {

                    try {

                        SharappMessage sharappMessage = new SharappMessage();
                        sharappMessage.typeMessage = CODIGO_DESCONEXION;
                        sharappMessage.userName = params[0];

                        oos.writeObject(sharappMessage);


                        oos.close();
                        ois.close();
                        s.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();

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

                new Desconexion().execute(nom_user);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
