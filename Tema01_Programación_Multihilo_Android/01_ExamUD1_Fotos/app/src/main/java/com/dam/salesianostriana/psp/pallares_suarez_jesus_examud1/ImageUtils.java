package com.dam.salesianostriana.psp.pallares_suarez_jesus_examud1;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Luismi on 25/10/2015.
 */
public class ImageUtils {

    /*
        Este método estático devuelve la URL de una imagen aleatoria del contenido
        popular de Instragram.

        Es el método a utilizar para obtener la URL de la imagen a descargar
     */
    public static String getRandomPhoto() {
        Random r = new Random();
        ArrayList<String> listaURLs = getPhotoURLs();
        return listaURLs.get(r.nextInt(listaURLs.size()));
    }

    /*
        Este método estático recibe un Bitmap y lo duvuelve rotado 90 grados
        en el sentido de las agujas del reloj.
     */
    public static Bitmap rotate90rigth(Bitmap src) {
        return RotateBitmap(src, 90);
    }



    /*
        Método privado auxiliar para la rotación de imágenes
     */
    private static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }


    /*
        Método privado auxiliar para obtener la información del API de Instragram
     */
    private static String readInstagramAPI() {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL("https://api.instagram.com/v1/media/popular?client_id=c15d8acfdb2a4511940092c9b208eab1");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /*
        Método privado auxiliar para parsear la información obtenida del API de Instragram
     */
    private static ArrayList<String> getPhotoURLs() {
        ArrayList<String> lista = new ArrayList<String>();
        String jsonInstagramFeed = readInstagramAPI();
        try {
            JSONObject jsonObject = (JSONObject) new JSONTokener(jsonInstagramFeed).nextValue();
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0; i < jsonArray.length(); i++) {
                lista.add(jsonArray.getJSONObject(i).getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }





}
