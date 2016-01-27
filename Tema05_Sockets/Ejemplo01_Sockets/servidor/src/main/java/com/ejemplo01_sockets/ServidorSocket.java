package com.ejemplo01_sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {


    public static void main(String[] args) {

        //Se crea el servidor
        ServerSocket serverSocket = null;
        Socket s = null;




        //Aceptamos la conexión de un cliente.
        //Esto debería hacerse en un bucle puesto que aquí solo estaría aceptando una conexión.

        try {

            serverSocket = new ServerSocket(10000);


            System.out.println("Servidor inicializado " + serverSocket.toString());


            s = serverSocket.accept();

            System.out.println("Conexión establecida" + s.toString());

            //Se instancia el BufferedReader sobre el flujo de entrada del socket
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));

            //El mensaje FIN será el que finalice la comunicación entre cliete y servidor.
            //Mientras tanto, vamos leyendo los mensajes y escribimos en la consola.

            String mensaje;

            while (!(mensaje = bufferedReader.readLine()).equalsIgnoreCase("FIN")) {
                System.out.println(">> " + mensaje);
            }

            //Finalizamos la conexión
            s.close();

            System.out.println("Conexión finalizada!");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
