package com.kltn.congphuc.giaohang.view

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.*
import com.kltn.congphuc.giaohang.R
import kotlinx.android.synthetic.main.activity_popup_cancel_voice.*
import com.kltn.congphuc.giaohang.dataRetrofit.datainvoiceSerializable
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.UnPaidInvoice
import com.kltn.congphuc.giaohang.model.sharedPreferences
import com.kltn.congphuc.giaohang.presenter.presenterSeenRequetCancelInvoice
import kotlinx.android.synthetic.main.custom_toast.*
import kotlinx.android.synthetic.main.row_goods_received.*


class PopupCancelVoice (): AppCompatActivity(),viewCancelInvoice {

    private var invoice:UnPaidInvoice?=null
    private var idshipper:String?="qưavadvsvsbgffnf"
    private var lyDo:String? =null

    override fun huyThanhCong() {
        Toast.makeText(this,"thanhcong",Toast.LENGTH_SHORT).show()
        val intent:Intent = Intent()
        intent.putExtra("data",1)
        setResult(Activity.RESULT_OK,intent)
        this.finish()

    }

    override fun huyThatBai() {
        Toast.makeText(this,"thatbai",Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popup_cancel_voice)
        supportActionBar!!.hide()
        val dm: DisplayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        val width:Int = dm.widthPixels
        val height:Int = dm.heightPixels
        window.setLayout((width * .9).toInt(),(height * .4).toInt())
        invoice= intent.getSerializableExtra("data") as UnPaidInvoice
        this.groupCancelinvoice.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            doOnDifficultyLevelChanged(group, checkedId) })
        sendCancel.setOnClickListener {
            val userInfor: sharedPreferences = sharedPreferences(this)
            val thongtin =userInfor.docThongTin()
            idshipper = thongtin.get(3)
            if (lyDo ==null)
            {
                Toast.makeText(this,"vui lòng chọn lý do hủy",Toast.LENGTH_SHORT).show()
            }
            else
            {
                textxlcencel.visibility = TextView.VISIBLE
                farmxlcancel.visibility = FrameLayout.INVISIBLE
                val pst = presenterSeenRequetCancelInvoice(this,invoice!!.id!!,invoice!!.store!!.id!!,idshipper!!,lyDo!!)
                pst.postCancelInvoice()
                //Toast.makeText(this,lyDo,Toast.LENGTH_SHORT).show()
            }
        }
        huyCancel.setOnClickListener {
            this.finish()
        }

    }


    private fun doOnDifficultyLevelChanged(group: RadioGroup?, checkedId: Int) {
            val id = group!!.checkedRadioButtonId
        if (id == R.id.kothayDC)
        {
            lyDo = "không tìm thấy địa chỉ khác hàng"
        }
        else {
            if (id == R.id.xeHu) {
                lyDo = "xe bị hư không thể giao đúng giờ"
            }
            else
            {
                if (id==R.id.hangKoDcNhan)
                {
                    lyDo = "khách không nhận hàng"
                }
            }
        }
    }
}
