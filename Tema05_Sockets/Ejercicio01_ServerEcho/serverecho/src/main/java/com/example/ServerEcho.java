package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEcho {


    public static void main(String[] args) {

        //Se crea el servidor
        ServerSocket serverSocket = null;
        Socket s = null;

        try {

            serverSocket = new ServerSocket(10000);
            System.out.println("Servidor inicializado " + serverSocket.toString());


            //Aceptamos la conexión de un cliente.
            //Esto debería hacerse en un bucle puesto que aquí solo estaría aceptando una conexión.
            s = serverSocket.accept();

            System.out.println("Conexión establecida" + s.toString());

            //Se instancia el BufferedReader sobre el flujo de entrada del socket
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));

            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);

            //El mensaje FIN será el que finalice la comunicación entre cliete y servidor.
            //Mientras tanto, vamos leyendo los mensajes y escribimos en la consola.
            String mensaje;

            while (!(mensaje = bufferedReader.readLine()).equalsIgnoreCase("FIN")) {
                System.out.println(">> " + mensaje);

                printWriter.println(mensaje);
                printWriter.flush();

            }

            //Finalizamos la conexión
            s.close();

            System.out.println("Conexión finalizada!");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
