package com.example.bulatgaliev.task1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by BulatGaliev on 26.04.16.
 */
// Prototype cache, not used yet
public class CacheModel implements Parcelable {
    private Map<String, Bitmap> cache;
    public CacheModel() {
        cache = Collections.synchronizedMap(new LinkedHashMap<String, Bitmap>(10, 1.5f, true));
    }
    public void put(String key, Bitmap value) {
        cache.put(key, value);
    }
    public Bitmap get(String key) {
        return cache.get(key);
    }

    public void saveCache() {
    }

    public void clearCache() {
        cache.clear();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        final int N = cache.size();
        dest.writeInt(N);
        if (N > 0) {
            for (Map.Entry<String, Bitmap> entry : cache.entrySet()) {
                dest.writeString(entry.getKey());
                Bitmap dat = entry.getValue();
                dest.writeString(BitMapToString(dat));
            }
        }
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static final Creator<CacheModel> CREATOR = new Creator<CacheModel>() {
        public CacheModel createFromParcel(Parcel source) {
            return new CacheModel(source);
        }
        public CacheModel[] newArray(int size) {
            return new CacheModel[size];
        }
    };



    private CacheModel(Parcel source) {
        final int N = source.readInt();
        for (int i=0; i < N; i++) {
            String key = source.readString();
            Bitmap bitmap = StringToBitMap(source.readString());
        }
    }
}
