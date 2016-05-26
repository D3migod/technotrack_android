package com.bulat_galiev.task3.Models;

import java.net.Socket;

/**
 * Created by BulatGaliev on 25.05.16.
 */
public class SocketSingleton {
    private static Socket socket;

    public static void setSocket(Socket socket){
        SocketSingleton.socket = socket;
    }

    public static Socket getSocket(){
        return SocketSingleton.socket;
    }
}
