package com.bulat_galiev.task3.Models;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by BulatGaliev on 25.05.16.
 */
public class SocketCreator extends AsyncTask<Void, Void, Socket> {
    private final static String IP_ADDRESS = "188.166.49.215";
    private final static int PORT = 7777;

    @Override
    protected Socket doInBackground(Void... params) {
        Socket socket = null;
        try {
            socket = new Socket(IP_ADDRESS, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}
