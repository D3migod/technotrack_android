package com.bulat_galiev.task3.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bulat_galiev.task3.Models.ClientTask;
import com.bulat_galiev.task3.Models.SocketCreator;
import com.bulat_galiev.task3.Models.SocketSingleton;
import com.bulat_galiev.task3.R;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Created by BulatGaliev on 12.05.16.
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);
        Socket socket = null;
        try {
            socket = (new SocketCreator()).execute().get();
        } catch (ExecutionException|InterruptedException e) {
            Log.e("Exception", "SplashScreenActivity: cannot open connection:" + e.toString());
        }
        SocketSingleton.setSocket(socket);
        new ClientTask(this, socket).execute();
    }
}