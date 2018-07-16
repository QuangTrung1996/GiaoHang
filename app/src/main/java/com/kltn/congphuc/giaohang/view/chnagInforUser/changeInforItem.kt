package com.kltn.congphuc.giaohang.view.chnagInforUser

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import com.kltn.congphuc.giaohang.R
import kotlinx.android.synthetic.main.activity_change_infor_item.*

class changeInforItem : AppCompatActivity() {

    private var resultCode:Int = 1
    private var test = "Đỗ Công Phúc"
    var code:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_infor_item)
        supportActionBar!!.hide()
        val dm: DisplayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width:Int = dm.widthPixels
        val height:Int = dm.heightPixels
        window.setLayout((width * .9).toInt(),(height * .4).toInt())
        val intent = intent
        test = intent.getStringExtra("data")
        code = intent.getIntExtra("code",0)
        xetdata()
    }

    private fun xetdata() {
        InforChange.append(test)
        xongChange.setOnClickListener {
            val intent: Intent = Intent()
            intent.putExtra("data",InforChange.text.toString())
            intent.putExtra("code",code)
            setResult(Activity.RESULT_OK,intent)
            this.finish()
        }
        HuyChange.setOnClickListener {
            this.finish()
        }
    }
}
