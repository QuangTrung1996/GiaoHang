package com.kltn.congphuc.giaohang.view.Main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.kltn.congphuc.giaohang.DetailBill
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.view.AdapterCallback


class HomeAdapter constructor(var context:Context, var listData:ArrayList<HomeData>, var callback:AdapterCallback): BaseAdapter() {
    inner class viewholder(row: View) {
        var idDơnHang:TextView
        var tenNguoiNhan:TextView
        var diaChi:TextView
        var khoiLuong:TextView
        var phiVanChuyen:TextView
        var xemchitiet :Button
        var nhandonhang:Button


        init {
            nhandonhang = row.findViewById(R.id.nhanDonHang)
            xemchitiet=row.findViewById(R.id.xemchitiet)as Button
            idDơnHang = row.findViewById<TextView>(R.id.id_donhang)
            tenNguoiNhan = row.findViewById<TextView>(R.id.tennguoinhan)
            diaChi =row.findViewById<TextView>(R.id.diachinguoinhan)
            khoiLuong =row.findViewById<TextView>(R.id.khoiluong)
            phiVanChuyen = row.findViewById<TextView>(R.id.sotiennhan)

        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view :View?
        val viewHolder: viewholder
        if (convertView == null) {
            var layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.row_home, null)
            viewHolder = viewholder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as viewholder
        }
        viewHolder.idDơnHang.text = listData.get(position).idDơnHang
        viewHolder.diaChi.text = listData.get(position).diaChi
        viewHolder.khoiLuong.text = listData.get(position).khoiLuong.toString().plus("KG")
        viewHolder.phiVanChuyen.text = listData.get(position).phiVanChuyen.toString().plus("Đ")
        viewHolder.tenNguoiNhan.text = listData.get(position).tenNguoiNhan
        viewHolder.xemchitiet.setOnClickListener {
            callback.ondetaill(position)
//            val intent = Intent(context,DetailBill::class.java)
//            context.startActivity(intent)
        }
        viewHolder.nhandonhang.setOnClickListener {
            try {
                callback!!.onMethodCallback()
            } catch (exception: ClassCastException) {
                // do something
            }

        }
        return view as View
    }

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