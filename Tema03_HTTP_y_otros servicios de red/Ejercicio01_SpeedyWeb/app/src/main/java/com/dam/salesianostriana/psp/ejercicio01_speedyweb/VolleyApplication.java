package com.dam.salesianostriana.psp.ejercicio01_speedyweb;

import android.app.Application;

import com.android.volley.RequestQueue;

public class VolleyApplication extends Application{

    public static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
    }
}
