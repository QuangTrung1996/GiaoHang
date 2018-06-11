package com.kltn.congphuc.giaohang.Main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import com.kltn.congphuc.giaohang.presenter.presenterLoadInvoince
import com.kltn.congphuc.giaohang.view.viewLoadInvoince
import org.jetbrains.annotations.Nullable

/**
 * Created by congp on 3/19/2018.
 */
class fragmentGoodsReceived: Fragment(),viewLoadInvoince {
    private var processLoadGoods:ProgressBar? = null
    private  var listDonHang :ArrayList<DataGoodsRecevied> = ArrayList()
    private lateinit var adapter:GoodsReceviedAdapter
    private var lvGoods: ListView? =null
    private var invoicesdata:InvoicesShiper = InvoicesShiper()


    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_goods_received, container, false);
        adapter = GoodsReceviedAdapter(this.context,listDonHang)
        lvGoods = view.findViewById(R.id.Lv_goods_received)
        processLoadGoods = view.findViewById(R.id.processLoadGoodsRecieve)as ProgressBar
        processLoadGoods!!.visibility = ProgressBar.VISIBLE
        lvGoods!!.visibility = ListView.INVISIBLE
        var prstload: presenterLoadInvoince = presenterLoadInvoince(this)
        prstload.loadVoice()

//        listDonHang.add(DataGoodsRecevied("Do cong phuc","asfdfhbdns",
//                "117/123/7nguyen huu canh phuong 22 quan binh thanh",100
//        ,2300000f,"0974995036"))
//
//        adapter.notifyDataSetChanged()
//        lvGoods!!.adapter = adapter
        return view

    }

    override fun loadthatbai() {
        super.loadthatbai()
        Toast.makeText(this.activity,"thử lại",Toast.LENGTH_SHORT).show()
    }

    override fun loadThanhCong(invoicesShiper: InvoicesShiper) {
        super.loadThanhCong(invoicesShiper)
        Log.d("fragmentHome","dc")
        invoicesdata=invoicesShiper
        docdata(invoicesShiper)
    }

    private fun docdata(invoicesShiper: InvoicesShiper) {
        for (i in 0..invoicesShiper!!.data!!.authenticatedShipper!!.unPaidInvoices!!.size-1)
        {

//                listDonHang.add(HomeData("sdafdssfdgtr","Đỗ Công Phuc","117/134/7 phuong 22 nguyên huu cnah",
//                23,5400000f))
            //Log.d("testid",invoinces!!.data!!.invoices!!.size.toString())
            listDonHang.add(DataGoodsRecevied(invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i)!!.id!!,invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i).customer!!.name!!,invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(0).tasks!!.location!!.address!!,
                    10,invoicesShiper!!.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i)!!.price!!.toFloat(),invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i).customer!!.phone!!))


        }
        adapter.notifyDataSetChanged()
        lvGoods!!.adapter = adapter
        lvGoods!!.visibility = ListView.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance(text:String):fragmentGoodsReceived{
            val f: fragmentGoodsReceived? = fragmentGoodsReceived()
            val b: Bundle= Bundle()
            b.putString("frmGR",text)
            f!!.arguments = b
            return f

        }
    }
}