package com.kltn.congphuc.giaohang.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.RadioGroup
import android.widget.Toast
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Data
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Notification
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Pushnotification
import com.kltn.congphuc.giaohang.presenter.PresenterSendFCMDelay
import kotlinx.android.synthetic.main.activity_popup_cancel_voice.*
import kotlinx.android.synthetic.main.activity_popup_delay.*

class PopupDelay : AppCompatActivity(),viewDelay {
    private var token:String? = null
    private var thoigian:String?=null
    private var lydo:String?=null
    override fun senThanhCong() {
        Toast.makeText(this,"thông tin đã được gởi đến khách hàng",Toast.LENGTH_LONG).show()
        this@PopupDelay.finish()
    }

    override fun senThatBai() {
        Toast.makeText(this,"vui lòng thử lại",Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_delay)
        supportActionBar!!.hide()
        val dm: DisplayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width:Int = dm.widthPixels
        val height:Int = dm.heightPixels
        window.setLayout((width * .9).toInt(),(height * .4).toInt())
        val a = intent
        token =a.getStringExtra("token")
        this.thoiGian.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            doOnDifficultyLevelChanged(group, checkedId) })
        this.lyDoDelay.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            lydo(group, checkedId) })
        huyDelay.setOnClickListener { this.finish() }
        goiThongBao.setOnClickListener {
            if (lydo != null && thoigian !=null) {
                val data = Data("giao hàng gạo", "giao hàng chậm".plus(thoigian) + lydo)
                val notification = Notification("giao hàng gạo", "giao hàng chậm".plus(thoigian) + lydo)
                val pushnotification = Pushnotification(token!!, notification, data)
                val pstSendFCM = PresenterSendFCMDelay(pushnotification, this)
                pstSendFCM.sendNotifiFCM()
            }
            else{
                val data = Data("giao hàng gạo", "giao hàng chậm")
                val notification = Notification("giao hàng gạo", "giao hàng chậm")
                val pushnotification = Pushnotification(token!!, notification, data)
                val pstSendFCM = PresenterSendFCMDelay(pushnotification, this)
                pstSendFCM.sendNotifiFCM()
            }
        }



    }

    private fun lydo(group: RadioGroup?, checkedId: Int) {
        val id = group!!.checkedRadioButtonId
        if(id==R.id.phut10)
        {
            thoigian = "10 phút"
        }
        if (id == R.id.phut15)
        {
            thoigian = "20 phút"
        }
        if (id == R.id.phut20)
        {
            thoigian = "30 phút"
        }
        if (id == R.id.phut30)
        {
            thoigian = "30 phút"
        }

    }

    private fun doOnDifficultyLevelChanged(group: RadioGroup?, checkedId: Int) {
        val id = group!!.checkedRadioButtonId
        if (id == R.id.ketxe)
        {
            lydo = "vì bị kẹt xe"
        }
        if (id == R.id.kotimdiachi)
        {
            lydo = "vì chưa tìm thấy địa chỉ"
        }
        if (id == R.id.xebihbat)
        {
            lydo = "vì vi phạm luật giao thông"
        }
        if (id == R.id.xehu)
        {
            lydo = "vì xe bị hư"
        }
    }
}
