package com.salesianostriana.psp.sharapp.server;


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

            if (messageAsObject instanceof  com.salesianostriana.psp.sharapp.SharappMessage) {
                com.salesianostriana.psp.sharapp.SharappMessage message = ( com.salesianostriana.psp.sharapp.SharappMessage) messageAsObject;
                u.userName = message.userName;
            } else {
                throw new ClassCastException();
            }

            //A�adimos al usuario a la colecci�n
            FileServer.usuarios.add(u);

            //Inicializamos el mecanismo de recepci�n de im�genes
            FileServer.executorService.execute(new Recieve(u));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }






    }

}
