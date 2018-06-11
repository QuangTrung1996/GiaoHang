package com.kltn.congphuc.giaohang.Main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.kltn.congphuc.giaohang.R
import org.jetbrains.annotations.Nullable

/**
 * Created by congp on 3/18/2018.
 */
class fragmentTransportFee: Fragment() {
    private  var listDonHang :ArrayList<DataTransportFee> = ArrayList()
    private lateinit var adapter:TransporrtFeeAdapter
    private var lvTransportFree: ListView? =null

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_transport_fee, container, false);
        lvTransportFree=view.findViewById(R.id.Lv_transport)as ListView
        listDonHang.add(DataTransportFee("sdafdssfdgtr",230000f,540f))
        listDonHang.add(DataTransportFee("fđbdf",300000f,540f))
        listDonHang.add(DataTransportFee("sdafdssfdgtr",13000f,40f))
        listDonHang.add(DataTransportFee("dsvqerhsgffh",230000f,570f))
        listDonHang.add(DataTransportFee("zxvsdgdvdsv",2156100f,50f))
        adapter = TransporrtFeeAdapter(this.context,listDonHang)
        adapter.notifyDataSetChanged()
        lvTransportFree!!.adapter = adapter
        return view

    }
    companion object {
        @JvmStatic
        fun newInstance(text:String): fragmentTransportFee {
            val f: fragmentTransportFee = fragmentTransportFee()
            val b: Bundle?= Bundle()
            b!!.putString("frmtránport",text)
            f.arguments = b
            return f

        }
    }
}