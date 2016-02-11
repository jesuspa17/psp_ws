package com.salesianostriana.psp.sendfiles.server;


import com.salesianostriana.psp.sendfiles.SharappMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Luismi on 02/02/2016.
 */
public class Connect implements Runnable {

    Socket s;
    User u;

    public Connect(Socket _s) {

        u = new User();
        s = _s;
        System.out.println("Conexión aceptada: " + s.toString());
    }

    @Override
    public void run() {


        try {
            //Inicializamos el usuario
            u.s = s;
            u.input = new ObjectInputStream(u.s.getInputStream());
            u.output = new ObjectOutputStream(u.s.getOutputStream());



            //Recibimos el mensaje de conexi�n, que incluye el
            //nombre de usuario
            Object messageAsObject = u.input.readObject();

            if (messageAsObject instanceof SharappMessage) {
                SharappMessage message = (SharappMessage) messageAsObject;
                u.userName = message.userName;
                System.out.println("Se ha conectado: " +  u.userName);
            } else {
                throw new ClassCastException();
            }

            //A�adimos al usuario a la colecci�n
            com.salesianostriana.psp.sendfiles.server.FileServer.usuarios.add(u);

            //Inicializamos el mecanismo de recepci�n de im�genes
            com.salesianostriana.psp.sendfiles.server.FileServer.executorService.execute(new com.salesianostriana.psp.sendfiles.server.Recieve(u));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }






    }

}
