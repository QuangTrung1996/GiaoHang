package com.kltn.congphuc.giaohang.view

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.kltn.congphuc.giaohang.view.Main.DataGoodsRecevied
import com.kltn.congphuc.giaohang.view.Main.GoodsReceviedAdapter
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.dataRetrofit.dataRecieveRiceType

class detailRecieveRiceTypeAdapter(var context: Context, var listData:ArrayList<dataRecieveRiceType>): BaseAdapter(){
    inner class viewholder(view: View)
    {
        var tenGao: TextView
        var soTuiGao: TextView
        var kLMoiTui: TextView
        var giaMoiTui: TextView
        var anhGao: ImageView
        init {
            tenGao = view.findViewById(R.id.tenGao)
            soTuiGao = view.findViewById(R.id.soTuiGao)
            kLMoiTui = view.findViewById(R.id.khoiLuongMotTui)
            giaMoiTui = view.findViewById(R.id.soTuiGao)
            anhGao = view.findViewById(R.id.imageGao)

        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view :View?
        val viewHolder: detailRecieveRiceTypeAdapter.viewholder
        if (convertView == null) {
            val layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.row_detail_invoive_loaigao, null)
            viewHolder = viewholder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as detailRecieveRiceTypeAdapter.viewholder
        }
        viewHolder.tenGao.text = listData.get(position).ten
        viewHolder.kLMoiTui.text = listData.get(position).khoiLuong
        viewHolder.giaMoiTui.text = listData.get(position).gia
        viewHolder.soTuiGao.text = listData.get(position).soTui
        Glide.with(context).load(listData.get(position).anhGao)
                .placeholder(R.drawable.shipgao)
                .error(R.drawable.shipgao)
                .centerCrop()
                .into(viewHolder.anhGao)
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