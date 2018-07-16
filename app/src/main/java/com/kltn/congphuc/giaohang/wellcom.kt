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
import android.widget.Toast
import android.R.attr.keySet
import com.google.firebase.iid.FirebaseInstanceId


class wellcom : AppCompatActivity() {
    var idvoice = "wellcom"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wellcom)
        onNewIntent(getIntent());
        supportActionBar!!.hide()
//        val refreshToken:String? = FirebaseInstanceId.getInstance().token
//        Log.d("tokenmyap",refreshToken)
        val trans: Animation = AnimationUtils.loadAnimation(this@wellcom,R.anim.aniwellcom)
        val transbirds: Animation = AnimationUtils.loadAnimation(this@wellcom,R.anim.anibirdsfly)
        imageViewflight!!.startAnimation(trans)
        imageViewWellcome.startAnimation(trans)
        imageViewbirds.startAnimation(transbirds)
        startMainActivity()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (intent != null && intent.extras != null) {
            val extras = intent.extras
            var someData = "wellcome"
            for (key:String in extras.keySet()) {
                Log.d("allkey",key)
                if (key.equals("title")) {
                    someData = extras!!.getString("title")
                    idvoice = someData
                    Log.d("title", someData)
                    Toast.makeText(this@wellcom, someData, Toast.LENGTH_SHORT).show()
                } else {
                    if (key.equals("titlenotifi")) {
                        someData = extras!!.getString("titlenotifi")
                        idvoice = someData
                        Toast.makeText(this@wellcom, someData, Toast.LENGTH_SHORT).show()
                    }
                }
            }
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
                        intent.putExtra("idvoice",idvoice)
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
