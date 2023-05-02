package com.example.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    val channel_Id = "channelId"
    val channel_Name = "channelName"
    val notificationid = 0

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        //main functipn me calling
        createNotificationChannel()

        //pending intent because when clicked on notification
        // is created beacuse when u click on notification you will be redirected to the app
        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_MUTABLE)

        val notification = NotificationCompat.Builder(this,channel_Id)          //things that will be diaplyed in the notification
            .setContentTitle("Aryan")
            .setContentText("Thanks for the Support")
            .setSmallIcon(R.drawable.baseline_emoji_emotions_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)

        val btn = findViewById<Button>(R.id.button)                 //while clicking notification will be displayed

        btn.setOnClickListener {
            notificationManager.notify(notificationid,notification)
        }

    }





    private fun createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {           //if the version is latest than oreo
            val channel = NotificationChannel(channel_Id, channel_Name, NotificationManager.IMPORTANCE_DEFAULT).apply{
               description = "this is my notification channel"

               //lightColor =color.RED //YOU CAN CHANGE THE COLOR
            }

            //creating manager
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)                                                        //calling the method which will do all the work


        }

    }



}