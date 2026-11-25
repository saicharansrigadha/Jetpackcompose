package com.example.jetpackcompose

import android.app.Application
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.initialize

class MyApplication : Application() {
    override fun onCreate(){
     super.onCreate()

        Firebase.initialize(this)

        if (FirebaseApp.getApps(this).isNotEmpty()) {
            Log.d("FIREBASE_TEST", "Firebase initialized successfully")
        } else {
            Log.e("FIREBASE_TEST", "Firebase initialization failed")
        }
    }
}