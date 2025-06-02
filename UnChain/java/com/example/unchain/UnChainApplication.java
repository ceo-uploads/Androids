package com.example.unchain;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class UnChainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
