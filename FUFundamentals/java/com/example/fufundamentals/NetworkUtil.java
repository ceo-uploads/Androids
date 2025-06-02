package com.example.fufundamentals;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtil {

    public interface NetworkCallback {
        void onResult(boolean canLoadData);
    }

    public static void canLoadFromServerAsync(NetworkCallback callback) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    HttpURLConnection urlConnection = (HttpURLConnection)
                            (new URL("https://www.google.com").openConnection());
                    urlConnection.setRequestMethod("HEAD");
                    urlConnection.setConnectTimeout(2000); // 2 seconds timeout
                    urlConnection.setReadTimeout(2000);
                    urlConnection.connect();
                    int responseCode = urlConnection.getResponseCode();
                    return (responseCode == 200 || responseCode == 301 || responseCode == 302);
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean canLoadData) {
                callback.onResult(canLoadData);
            }
        }.execute();
    }
}
