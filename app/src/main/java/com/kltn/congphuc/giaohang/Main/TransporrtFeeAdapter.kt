package com.kltn.congphuc.giaohang.Main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.kltn.congphuc.giaohang.R

class TransporrtFeeAdapter constructor(var context:Context, var listdataFee:ArrayList<DataTransportFee>): BaseAdapter() {

    inner class viewholder(view: View)
    {
        var idDonHang:TextView
        var soTienNhan:TextView
        var phiVanChuyen:TextView
        init {
            idDonHang = view.findViewById(R.id.id_donhangtransport)
            soTienNhan = view.findViewById(R.id.sotiennhantransport)
            phiVanChuyen = view.findViewById(R.id.phivanchuyentransport)
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view :View?
        val viewHolder: TransporrtFeeAdapter.viewholder
        if (convertView == null) {
            var layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.row_transport_fee, null)
            viewHolder = viewholder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as viewholder
        }
        viewHolder.idDonHang.text = listdataFee.get(position).idDonHang
        viewHolder.soTienNhan.text = listdataFee.get(position).soTienNhan.toString().plus(" Đ")
        viewHolder.phiVanChuyen.text = listdataFee.get(position).phiaVanChuyen.toString().plus("Đ")
        return view as View
    }

    override fun getItem(p0: Int): Any {
        return listdataFee.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return listdataFee.size
    }
}