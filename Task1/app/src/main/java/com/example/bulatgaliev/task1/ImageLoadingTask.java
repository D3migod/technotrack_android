package com.example.bulatgaliev.task1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by BulatGaliev on 23.04.16.
 */
public class ImageLoadingTask extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;
    Context context;

    ImageLoadingTask(ImageView imageView, Context context) {
        this.imageView = imageView;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        imageView.setImageDrawable(context.getResources().getDrawable(R.mipmap.launcher_horse));
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String urlString = params[0];
        Bitmap bitmap = null;
        if (bitmap != null) {
            return bitmap;
        }
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e("Exception", "LoaderModel: " + e.toString());
            return bitmap;
        }
        HttpURLConnection urlConnection;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            Log.e("Exception", "LoaderModel: " + e.toString());
            return bitmap;
        }
        try {
            urlConnection.setChunkedStreamingMode(0);
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                bitmap = readStream(in);
                return bitmap;
            } catch (IOException | JSONException e) {
                Log.e("Exception", "LoaderModel: " + e.toString());
                return null;
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    private Bitmap readStream(InputStream in) throws IOException, JSONException {
        try {
            return BitmapFactory.decodeStream(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
