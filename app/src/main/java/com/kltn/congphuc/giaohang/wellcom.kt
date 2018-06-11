package com.kltn.congphuc.giaohang

import android.app.NotificationManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.kltn.congphuc.giaohang.model.sharedPreferences
import com.kltn.congphuc.giaohang.myFirebaseIdService.MyFirebasemessage
import kotlinx.android.synthetic.main.activity_wellcom.*
import android.widget.TextView



class wellcom : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wellcom)
        onNewIntent(getIntent());
//        var a = "qfewfwef"
//        val b = intent
//        if (b.getStringExtra("title") !=null){
//            a = b.getStringExtra("title")}

        supportActionBar!!.hide()
        val trans: Animation = AnimationUtils.loadAnimation(this@wellcom,R.anim.aniwellcom)
        val transbirds: Animation = AnimationUtils.loadAnimation(this@wellcom,R.anim.anibirdsfly)
        imageViewflight!!.startAnimation(trans)
        imageViewWellcome.startAnimation(trans)
        imageViewbirds.startAnimation(transbirds)
        startMainActivity()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val extras = intent!!.getExtras()
        if (extras != null) {
            Log.d("wellcome", extras.size().toString())
            for (key:String in extras.keySet()) {
                Log.d("wellcome",key)
                if (key.equals("titlenotifi")) {
                    // extract the extra-data in the Notification
                    val msg = extras.getString("titlenotifi")
                    Log.d("wellcome", msg)
                } else {
                    Log.d("wellcome", "sao ta")

                }
            }
        }
        else
        {
            Log.d("wellcome","the déo nào")
        }
    }
    private fun startMainActivity() {
        val thread = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(4000) // set thoi gian dong Activity
                    val laytt = sharedPreferences(this@wellcom)
                    val a = laytt.docThongTin()
                    if (a.get(0) == "KLTN-Ugao")
                    {
                        val intent = Intent(this@wellcom, LogIn::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        val intent = Intent(this@wellcom, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        thread.start()
    }
}
