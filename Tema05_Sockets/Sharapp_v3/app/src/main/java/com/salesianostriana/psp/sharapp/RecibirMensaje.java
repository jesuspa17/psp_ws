package com.salesianostriana.psp.sharapp;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * Created by Jesus Pallares on 10/02/2016.
 */
public class RecibirMensaje implements Runnable{

    Handler handler;
    ObjectInputStream ois;

    public RecibirMensaje(ObjectInputStream ois, Handler handler){
        this.ois = ois;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {

            while (true){
                if(ois!=null){
                    Object objRecibido = ois.readObject();
                    if (objRecibido instanceof com.salesianostriana.psp.sendfiles.SharappMessage) {
                        Message msg = new Message();
                        com.salesianostriana.psp.sendfiles.SharappMessage obj = (com.salesianostriana.psp.sendfiles.SharappMessage) objRecibido;
                        msg.obj = obj;
                        handler.sendMessage(msg);

                    } else {

                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
