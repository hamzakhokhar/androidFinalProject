package com.example.hamzakhokhar.finalproject

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.SupportMapFragment

class SplashScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object: Thread() {
            override fun run() {
                try {
                    Thread.sleep(5000)

                    val intent = Intent(baseContext, Login::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()


    }

}
