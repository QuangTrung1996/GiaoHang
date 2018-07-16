package com.kltn.congphuc.giaohang.myFirebaseIdService

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.kltn.congphuc.giaohang.MainActivity
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.wellcom

class MyFirebasemessage: FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage?) {
        super.onMessageReceived(p0)
        showNotification(p0!!.notification,p0!!.data!!.get("title")!!)
    }

    private fun showNotification(notification: RemoteMessage.Notification?,string: String) {
        var intent:Intent = Intent(this,wellcom::class.java)
        intent.putExtra("titlenotifi",string)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or  Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent = PendingIntent.getActivity(this,intent.filterHashCode(),intent,PendingIntent.FLAG_UPDATE_CURRENT)
       val notificationcopact = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(notification!!.title)
                .setContentText(notification.body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
        Log.d("MyFirebasemessage",notification.body.toString())
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0,notificationcopact.build())

    }

}