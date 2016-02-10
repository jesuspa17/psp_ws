package com.salesianostriana.psp.sharapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jesús Pallares on 10/02/2016.
 */
public class Utils {

    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;

    public static void initializePreferences(Context context){
        preferences = context.getSharedPreferences("sharapp",context.MODE_PRIVATE);
        editor = preferences.edit();
    }
}
