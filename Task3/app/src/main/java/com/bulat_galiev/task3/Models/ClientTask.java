package com.bulat_galiev.task3.Models;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.bulat_galiev.task3.Activities.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by BulatGaliev on 13.05.16.
 */

public class ClientTask extends AsyncTask<JSONObject, Void, JSONObject> {


    private JSONObject jsonData;
    private Context context;
    private String error;
    private Socket socket;

    public ClientTask(Context context, Socket socket) {
        this.context = context;
        this.socket = socket;
    }
    @Override
    protected JSONObject doInBackground(JSONObject... params) {
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        if (params.length != 0) {
            jsonData = params[0];
        }

        try {
            // Create a new Socket instance and connect to host
            //socket = new Socket(IP_ADDRESS, PORT);
            if (jsonData != null) {
                bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
                Log.e("Send json", jsonData.toString());
                byte[] outputData = jsonData.toString().getBytes(Charset.forName("UTF-8"));
                bufferedOutputStream.write(outputData);
                bufferedOutputStream.flush();
            }


            bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            try {
                byte[] inputData = new byte[35000];
                int readBytes = bufferedInputStream.read(inputData);
                if (readBytes != -1) {
                    String stringResponse = new String(inputData, "UTF-8");
                    JSONObject jsonResponse = new JSONObject(stringResponse);


                    Log.e("Response", "ClientTask: " + jsonResponse.toString());
                    return jsonResponse;
                } else {
                    Log.e("Response", "ClientTask: Response is NULL");
                    return null;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            Log.e("Exception", "ClientTask: " + e.toString());
            return null;
        } finally {
            /*// close socket


            // close input stream
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    Log.e("Exception", "ClientTask: " + e.toString());
                }
            }

            // close output stream
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    Log.e("Exception", "ClientTask: " + e.toString());
                }
            }*/
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        try {
            if (result!=null && result.has("data")) {
                JSONObject data = result.getJSONObject("data");
                if (data.has("status") && !(data.getInt("status") == 0)) {
                    String error = data.getString("error");
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (JSONException e) {
            Toast.makeText(context, "Error retreiving error!", Toast.LENGTH_SHORT).show();
            Log.e("Exception", "ClientTask: " + e.toString());
        }

        try {
            if (result!=null && result.has("action") && result.getString("action").equals("welcome")) {
                Toast.makeText(context, "Welcome aboard!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                context.startActivity(intent);
                Activity activity = (Activity) context;
                activity.finish();
                if (SocketSingleton.getSocket().isClosed()) {
                    Log.e("Warning", "ClientTask: socket is closed");
                }
            }
        } catch (JSONException e) {
            Toast.makeText(context, "You are not welcome here!", Toast.LENGTH_SHORT).show();
            Log.e("Exception", "ClientTask: " + e.toString());
        }
    }
}