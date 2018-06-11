package com.kltn.congphuc.giaohang.Main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.kltn.congphuc.giaohang.R
import java.time.temporal.TemporalAccessor

class GoodsReceviedAdapter constructor(var context:Context,var listData:ArrayList<DataGoodsRecevied>): BaseAdapter() {

    inner class viewholder(view: View)
    {
        var idDonHang: TextView
        var soTienNhan: TextView
        var diaChi: TextView
        var soDienThoai:TextView
        var tenNguoiNhan:TextView
        var khoiLuong:TextView
        init {
            idDonHang = view.findViewById(R.id.id_donhangreceved)
            soTienNhan = view.findViewById(R.id.sodienthoaireceved)
            diaChi = view.findViewById(R.id.diachinguoinhanreceved)
            soDienThoai=view.findViewById(R.id.sodienthoaireceved)
            tenNguoiNhan = view.findViewById(R.id.tennguoinhanreceved)
            khoiLuong = view.findViewById(R.id.khoiluongreceved)

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view :View?
        val viewHolder: GoodsReceviedAdapter.viewholder
        if (convertView == null) {
            var layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.row_goods_received, null)
            viewHolder = viewholder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as viewholder
        }
        viewHolder.idDonHang.text = listData.get(position).idDonHang
        viewHolder.soTienNhan.text = listData.get(position).soTienNhan.toString().plus(" Đ")
        viewHolder.tenNguoiNhan.text = listData.get(position).tennguoinhan
        viewHolder.diaChi.text = listData.get(position).diaChi
        viewHolder.khoiLuong.text = listData.get(position).khoiluong.toString().plus(" KG")
        viewHolder.soDienThoai.text = listData.get(position).soDienThoai
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