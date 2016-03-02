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

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    MensajeAdapter mensajeAdapter;

    private static int FILE_CODE = 1;
    private static int CODIGO_CONEXION = 1;
    private static int CODIGO_ENVIO = 2;
    private static int CODIGO_DESCONEXION = 3;

    //192.168.65.2
    //172.27.60.7
    private static String IP_SERVIDOR = "172.27.0.41";

    List<CuerpoMensaje> lista;
    String nom_user;

    Socket s;
    //Flujos necesarios.
    ObjectOutputStream oos;
    ObjectInputStream ois;
    BufferedInputStream bis;
    ByteArrayOutputStream baos;
    ExecutorService executorService;

    //Handler que realiza los cambios en la interfaz de usuario cada vez que recibimos un mensaje
    //del servidor.
     Handler puente = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //Se recibe el mensaje
            com.salesianostriana.psp.sendfiles.SharappMessage sharappMessage = (com.salesianostriana.psp.sendfiles.SharappMessage) msg.obj;

            //Elementos recibidos a mostrar en el recyclerView
            String username = sharappMessage.userName;
            Date fecha = sharappMessage.date;
            Bitmap b = Utils.decodeBitmapSize(sharappMessage.content, 300);

            Log.i("HANDLER_USERNAME", sharappMessage.userName);
            Log.i("HANDLER_DATE", sharappMessage.date.toString());

            //Se guarda la imagen en la memoria interna del teléfono.
            Utils.saveImage(MainActivity.this, sharappMessage.fileName, sharappMessage.content);

            //Comprobación de que si el nombre de usuario que se recibe es del de nosotros,
            //la imagen se inserte en el recyclerView de una manera animada, si no lo es, se insertará de otra manera.
            if(username.equalsIgnoreCase(nom_user)){
                recyclerView.setItemAnimator(new SlideInLeftAnimator());
            }else{
                recyclerView.setItemAnimator(new SlideInRightAnimator());
            }

            //Se le da propiedades al movimiento de inserción.
            recyclerView.getItemAnimator().setAddDuration(700);
            recyclerView.getItemAnimator().setMoveDuration(700);
            recyclerView.getItemAnimator().setChangeDuration(700);

            //Se añade la una lista de clase CuerpoMensaje para mostrarlos en la lista.
            mensajeAdapter.add(new CuerpoMensaje(username, fecha,b),lista.size());

            //Desplaza la lista automáticamente a la última posición
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    recyclerView.smoothScrollToPosition(mensajeAdapter.getItemCount());
                }
            });

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Inicializo elementos del recycler.
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Inicializo las preferencias
        Utils.initializePreferences(this);

        //Inicializo los elementos necesarios para el adapter del Recycler
        lista = new ArrayList<>();
        mensajeAdapter =new MensajeAdapter(lista);
        recyclerView.setAdapter(mensajeAdapter);

        //Obtengo el nombre de las preferencias y lanzo el Asynctask de conexión.
        nom_user = Utils.preferences.getString("nom_user", null);

        //Realizo la conexión al servidor con el nombre de usuario obtenido
        new Conexion().execute(nom_user);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abre activityForResult del FilePicker
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

        //AsyncTask que implementa el mensaje de conexión que se le manda al servidor.
        private class Conexion extends AsyncTask<String, Void, Void> {

            @Override
            protected Void doInBackground(String... params) {
                if (params != null) {
                    try {
                        //Se inicializa el socket.
                        s = new Socket(IP_SERVIDOR, 10000);

                        //Se inicializan los flujos de lectura y escritura.
                        oos = new ObjectOutputStream(s.getOutputStream());
                        ois = new ObjectInputStream(s.getInputStream());

                        //Se crea el objeto a enviar.
                        com.salesianostriana.psp.sendfiles.SharappMessage sharappMessage = new com.salesianostriana.psp.sendfiles.SharappMessage();
                        sharappMessage.typeMessage = CODIGO_CONEXION;
                        sharappMessage.userName = params[0];

                        //Se envia el mensaje serializado
                        oos.writeObject(sharappMessage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                //Ejecuto el executorService que se encargará de que los mensajes puedan recibirse en cualquier momento.
                executorService = Executors.newCachedThreadPool();
                executorService.execute(new RecibirMensaje(ois,puente));
            }
        }

        //AsyncTask que implementa el envío de mensajes al servidor.
        private class EnviarMensaje extends AsyncTask<String, Void, Void> {

            @Override
            protected Void doInBackground(String... params) {

                if (params != null) {

                    //Recibimos los parámetros que se le pasan, el nombre y la ruta de la imagen.
                    String nomRecibido = params[0];
                    String rutaImagen = params[1];

                    try {

                        int TAM = 64 * 1024;
                        //Se inicializa el flujo de lectura que leerá la imagen recibida.
                        bis = new BufferedInputStream(new FileInputStream(rutaImagen));
                        //Se inicializa el flujo que transformará la imagen en un array de bytes.
                        baos = new ByteArrayOutputStream();

                        //se indica el tamaño del buffer
                        byte[] buffer = new byte[TAM];
                        int c;

                        //se lee la información de la imagen y se va escribiendo en el buffer.
                        while ((c = bis.read(buffer, 0, TAM)) != -1) {
                            baos.write(buffer, 0, c);
                        }

                        //Creo el objeto a enviar.
                        com.salesianostriana.psp.sendfiles.SharappMessage sharappMessage = new com.salesianostriana.psp.sendfiles.SharappMessage();
                        sharappMessage.typeMessage = CODIGO_ENVIO;
                        sharappMessage.userName = nomRecibido;
                        sharappMessage.fileName = rutaImagen.split("/")[rutaImagen.split("/").length - 1];
                        sharappMessage.content = baos.toByteArray();
                        sharappMessage.message = "";
                        sharappMessage.date = Calendar.getInstance().getTime();

                        //Se envia.
                        oos.writeObject(sharappMessage);

                        //Cierro los flujos inicializados anteriormente.
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

        //AsyncTask que implementa el mensaje de desconexión.
        private class Desconexion extends AsyncTask<String, Void, Void> {
            @Override
            protected Void doInBackground(String... params) {
                if (params != null) {
                    try {
                        //Recibimos el nombre de usuario a través de los parámetros y
                        //se crea el mensaje a enviar.
                        com.salesianostriana.psp.sendfiles.SharappMessage sharappMessage = new com.salesianostriana.psp.sendfiles.SharappMessage();
                        sharappMessage.typeMessage = CODIGO_DESCONEXION;
                        sharappMessage.userName = params[0];

                        //Enviamos el objeto.
                        oos.writeObject(sharappMessage);

                        //Cerramos todos los fujos.
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
            //Envío el mensaje de desconexión.
            new Desconexion().execute(nom_user);
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
                //Envio el mensaje de desconexión, cierro el activity y limpio las preferencias.
                new Desconexion().execute(nom_user);
                MainActivity.this.finish();
                startActivity(new Intent(MainActivity.this,RegistroActivity.class));
                Utils.editor.clear();
                Utils.editor.apply();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
