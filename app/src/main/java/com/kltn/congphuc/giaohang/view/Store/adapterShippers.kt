package com.kltn.congphuc.giaohang.view.Store

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.view.Main.DataGoodsRecevied
import com.kltn.congphuc.giaohang.view.Main.GoodsReceviedAdapter
import com.kltn.congphuc.giaohang.view.callBackGoodsRecieved

class adapterShippers(var context: Context, var listData:ArrayList<dataShippers>): BaseAdapter() {
    inner class viewHolder (view:View){
        var tenShipper:TextView
        var email:TextView
        var sDT:TextView
        var anhShipper:ImageView
        init {
            tenShipper = view.findViewById(R.id.nameShippers)
            anhShipper = view.findViewById(R.id.imageShippers)
            email = view.findViewById(R.id.emailShippers)
            sDT = view.findViewById(R.id.phoneShippers)
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view :View?
        val viewHolder: viewHolder
        if (convertView == null) {
            val layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.row_store_shipper, null)
            viewHolder = viewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as viewHolder
        }
        viewHolder.tenShipper.text = listData.get(position).ten
        viewHolder.sDT.text = listData.get(position).sdt
        viewHolder.email.text = listData.get(position).email
        Glide.with(context).load(listData.get(position).img)
                .placeholder(R.drawable.shipgao)
                .error(R.drawable.shipgao)
                .centerCrop()
                .into(viewHolder.anhShipper)
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