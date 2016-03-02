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
            //Este código siempre estará en ejecución, para que podamos recibir mensajes constantemente.
            while (true){
                if(ois!=null){
                    //Obtengo el objeto recibido.
                    Object objRecibido = ois.readObject();
                    //Compruebo que sea de tipo "SharappMessage"
                    if (objRecibido instanceof com.salesianostriana.psp.sendfiles.SharappMessage) {
                        //Creo el mensaje que se le enviará al Handler
                        Message msg = new Message();
                        //Creo el objeto que se le enviará al Handler a través del mensaje instanciado antes.
                        com.salesianostriana.psp.sendfiles.SharappMessage obj = (com.salesianostriana.psp.sendfiles.SharappMessage) objRecibido;
                        //Asocio el mensaje al objeto.
                        msg.obj = obj;
                        //Envío el mensaje al handler que se ejecutará en este caso en el MainActivity.
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
