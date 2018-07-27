package com.kltn.congphuc.giaohang.view.Main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.view.callBackGoodsRecieved
import java.text.SimpleDateFormat
import java.time.temporal.TemporalAccessor
import kotlin.concurrent.fixedRateTimer

class GoodsReceviedAdapter constructor(var context:Context, var listData:ArrayList<DataGoodsRecevied>, var callGoodsRecieved:callBackGoodsRecieved): BaseAdapter() {

    inner class viewholder(view: View)
    {
        var thoigiangiao:TextView
        var xemchitiet:TextView
        var delay:Button
        var idDonHang: TextView
        var soTienNhan: TextView
        var diaChi: TextView
        var soDienThoai:TextView
        var tenNguoiNhan:TextView
        var khoiLuong:TextView
        var huyDonHang:Button
        var goiKhachHang:ImageButton
        var giaohang:Button
        init {
            giaohang = view.findViewById(R.id.xacnhangiaohang)
            xemchitiet = view.findViewById(R.id.xemchitietRecieve)
            goiKhachHang = view.findViewById(R.id.callPhonenumble)
            huyDonHang = view.findViewById(R.id.huydonhang)
            delay = view.findViewById(R.id.trihoan)
            idDonHang = view.findViewById(R.id.id_donhangreceved)
            soTienNhan = view.findViewById(R.id.sotiennhanreceved)
            diaChi = view.findViewById(R.id.diachinguoinhanreceved)
            soDienThoai=view.findViewById(R.id.sodienthoaireceved)
            tenNguoiNhan = view.findViewById(R.id.tennguoinhanreceved)
            khoiLuong = view.findViewById(R.id.khoiluongreceved)
            thoigiangiao = view.findViewById(R.id.thoigiangiao)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view :View?
        val viewHolder: viewholder
        if (convertView == null) {
            val layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.row_goods_received, null)
            viewHolder = viewholder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as viewholder
        }
        viewHolder.idDonHang.text =(position+1).toString().plus(". "+ listData.get(position).idDonHang)
        viewHolder.soTienNhan.text = listData.get(position).soTienNhan.toString().plus(".000 đ")
        viewHolder.tenNguoiNhan.text = listData.get(position).tennguoinhan
        viewHolder.diaChi.text = listData.get(position).diaChi
        viewHolder.khoiLuong.text = listData.get(position).khoiluong.toString().plus(" KG")
        viewHolder.soDienThoai.text = listData.get(position).soDienThoai
        viewHolder.delay.setOnClickListener {
            val a = callGoodsRecieved
            callGoodsRecieved.callDelay(listData.get(position).token)
        }
        viewHolder.huyDonHang.setOnClickListener {
            val a = callGoodsRecieved
            callGoodsRecieved.callCancel(position)
        }
        viewHolder.goiKhachHang.setOnClickListener {
            val a = callGoodsRecieved
            callGoodsRecieved.callCallPhoneNumbe(position)
        }
        viewHolder.xemchitiet.setOnClickListener {
            val a = callGoodsRecieved
            callGoodsRecieved.callDetail(position)
        }
        viewHolder.giaohang.setOnClickListener {
            val a = callGoodsRecieved
            callGoodsRecieved.callConfirmDelivery(position)
        }
       val gio= doiTG(listData.get(position).estimationTime)
        viewHolder.thoigiangiao.text = (gio)

        return view as View
    }

    @SuppressLint("SimpleDateFormat")
    private fun doiTG(estimationTime: String):String {

        val sdf_date_bd : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        var ngaybd =sdf_date_bd.format(estimationTime.toLong())
        return ngaybd

    }

//    private fun doiTG()
//    {
////        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
////        val myDate = simpleDateFormat.parse(rawQuestion.getString("AskDateTime"))
//
//    }
    override fun getItem(p0: Int): Any {
        return listData.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return listData.size
    }
}