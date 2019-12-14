package com.example.playermusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent




//https://stackoverflow.com/questions/48140010/android-media-player-streaming-not-working

//https://stackoverflow.com/questions/52473974/binding-playerview-with-simpleexoplayer-from-a-service

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonStart.setOnClickListener{
            startService(Intent(this, MyService::class.java))
            showNotification()
        }

        buttonStop.setOnClickListener{
            stopService(Intent(this, MyService::class.java))
            showNotification()
        }

    }


    fun showNotification() {
        //MyNotification(this)
        //finish()
    }
}
