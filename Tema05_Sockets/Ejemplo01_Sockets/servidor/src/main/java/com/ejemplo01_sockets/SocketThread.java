package com.ejemplo01_sockets;

import java.net.Socket;

/**
 * Created by Jesús Pallares on 25/01/2016.
 */
public class SocketThread implements Runnable {

    Socket s;

    public SocketThread(Socket s_){
        this.s = s_;
    }

    @Override
    public void run() {


    }
}
