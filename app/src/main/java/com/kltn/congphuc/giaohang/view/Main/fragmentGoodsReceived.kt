package com.kltn.congphuc.giaohang.view.Main

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
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
import org.jetbrains.annotations.Nullable
import android.net.Uri.fromParts
import com.kltn.congphuc.giaohang.DetailBill
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.UnPaidInvoice
import com.kltn.congphuc.giaohang.model.sharedPreferences
import com.kltn.congphuc.giaohang.view.*
import java.util.*
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.widget.TextView
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Data
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Notification
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Pushnotification
import com.kltn.congphuc.giaohang.model.ConectDatabaseSQLite
import com.kltn.congphuc.giaohang.presenter.presenterConfirmDelivery


/**
 * Created by congp on 3/19/2018.
 */
class fragmentGoodsReceived: Fragment(),viewLoadInvoince,callBackGoodsRecieved,viewConfirmDeliver {
    val databasename = "donHang.sqlite"
    var database: SQLiteDatabase? = null
    var tongkhoiluongreceived:TextView?=null
    private var idVoiceDelived:String?=null
    override fun xacNhanThanhCong() {
        val a1 = sharedPreferences(this.context)
        val infor = a1.docThongTin()
        val userInfor: sharedPreferences = sharedPreferences(this.context)
        val thongtin =userInfor.docThongTin()
        val calender: Calendar = Calendar.getInstance()
        val ngay = calender.get(Calendar.DAY_OF_MONTH)
        val thang = calender.get(Calendar.MONTH)
        val nam = calender.get(Calendar.YEAR)
        database = ConectDatabaseSQLite().initDatabase(this.activity,databasename);
        val values = ContentValues()
        val id = thongtin[3]
        values.put("idShipper", id)
        values.put("date", (ngay.toString())+(thang.toString())+(nam.toString()))
        values.put("idInvoice", idVoiceDelived)
        database!!.insert("delivered", null, values)

        processLoadGoods!!.visibility = ProgressBar.VISIBLE
        lvGoods!!.visibility = ListView.INVISIBLE
        userName = thongtin.get(5)
        val pass =  thongtin.get(6)
        val prstload: presenterLoadInvoince = presenterLoadInvoince(userName,pass,this)
        prstload.loadVoice()
    }

    override fun xacNhanThatBai() {
        Toast.makeText(this.context,"vui lòng xác nhận lại", Toast.LENGTH_LONG).show()
    }

    override fun callConfirmDelivery(position: Int) {
        idVoiceDelived = invoicesdata.data!!.authenticatedShipper!!.unPaidInvoices!!.get(position).id
      val a = presenterConfirmDelivery(this,idVoiceDelived!!)
        a.sendRequest()
    }


    override fun callDetail(position: Int) {
        val a:UnPaidInvoice = invoicesdata.data!!.authenticatedShipper!!.unPaidInvoices!!.get(position)
        val intent = Intent(this.context,DetaiVoiceUnpaid::class.java)
        intent.putExtra("data",a)
        startActivity(intent)

    }

    override fun callCancel(position: Int) {
        val a:UnPaidInvoice = invoicesdata.data!!.authenticatedShipper!!.unPaidInvoices!!.get(position)
        val intent = Intent(this.context,PopupCancelVoice::class.java)
        intent.putExtra("data",a)
        startActivityForResult(intent,RequestCode)
    }

    override fun callCallPhoneNumbe(position: Int) {
        super.callCallPhoneNumbe(position)
        val phone = invoicesdata.data!!.authenticatedShipper!!.unPaidInvoices!!.get(position).customer!!.phone
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode ==RequestCode && resultCode == RESULT_OK && data !=null)
        {
            processLoadGoods!!.visibility = ProgressBar.VISIBLE
            lvGoods!!.visibility = ListView.INVISIBLE
            val a = sharedPreferences(this.context)
            val infor = a.docThongTin()
            val userInfor: sharedPreferences = sharedPreferences(this.context)
            val thongtin =userInfor.docThongTin()
            userName = thongtin.get(5)
            val pass =  thongtin.get(6)
            val prstload: presenterLoadInvoince = presenterLoadInvoince(userName,pass,this)
            prstload.loadVoice()
        }
    }
    private val RequestCode = 1000
    private var processLoadGoods:ProgressBar? = null
    private  var listDonHang :ArrayList<DataGoodsRecevied> = ArrayList()
    private lateinit var adapter: GoodsReceviedAdapter
    private var lvGoods: ListView? =null
    private var invoicesdata:InvoicesShiper = InvoicesShiper()
    private var userName:String = "Royce22@yahoo.com"
    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_goods_received, container, false);
        tongkhoiluongreceived = view.findViewById(R.id.tongkhoiluongreceived)
        adapter = GoodsReceviedAdapter(this.context, listDonHang, this)
        lvGoods = view.findViewById(R.id.Lv_goods_received)
        processLoadGoods = view.findViewById(R.id.processLoadGoodsRecieve)as ProgressBar
        processLoadGoods!!.visibility = ProgressBar.VISIBLE
        lvGoods!!.visibility = ListView.INVISIBLE
//        val a = sharedPreferences(this.context)
//        val infor = a.docThongTin()
        val userInfor: sharedPreferences = sharedPreferences(this.context)
        val thongtin =userInfor.docThongTin()
        userName = thongtin.get(5)
        val pass =  thongtin.get(6)
        val prstload: presenterLoadInvoince = presenterLoadInvoince(userName,pass,this)
        prstload.loadVoice()

//        listDonHang.add(DataGoodsRecevied("Do cong phuc","asfdfhbdns",
//                "117/123/7nguyen huu canh phuong 22 quan binh thanh",100
//        ,2300000f,"0974995036"))
//
//        adapter.notifyDataSetChanged()
//        lvGoods!!.adapter = adapter
        return view

    }

    override fun callDelay(token: String) {
        super.callDelay(token)
        val intent = Intent(this.activity,PopupDelay::class.java)
        intent.putExtra("token",token)
        this.startActivity(intent)
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
        var tong:Double = 0.0
        listDonHang.clear()
        for (i in 0..invoicesShiper!!.data!!.authenticatedShipper!!.unPaidInvoices!!.size-1)
        {
            for (j in 0 .. invoicesShiper!!.data!!.authenticatedShipper!!.unPaidInvoices!![i].products!!.size-1){
            tong = tong+ invoicesShiper!!.data!!.authenticatedShipper!!.unPaidInvoices!![i].products!![j]!!.quantity!!}

//                listDonHang.add(HomeData("sdafdssfdgtr","Đỗ Công Phuc","117/134/7 phuong 22 nguyên huu cnah",
//                23,5400000f))
            //Log.d("testid",invoinces!!.data!!.invoices!!.size.toString())
            listDonHang.add(DataGoodsRecevied(invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i)!!.id!!, invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i).customer!!.name!!, invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i).tasks!!.location!!.address!!,
                    10, invoicesShiper!!.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i)!!.price!!, invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i).customer!!.phone!!, invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i).customer!!.token!!))
//            listDonHang.add(DataGoodsRecevied(invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i)!!.id!!, invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i).customer!!.name!!, invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i).tasks!!.location!!.address!!,
//                    10, invoicesShiper!!.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i)!!.price!!, invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i).customer!!.phone!!, "shnushgucshc"))

        }
        tongkhoiluongreceived!!.text = tong.toString().plus(" KG")

        adapter.notifyDataSetChanged()
        lvGoods!!.adapter = adapter
        processLoadGoods!!.visibility = ProgressBar.INVISIBLE
        lvGoods!!.visibility = ListView.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance(text:String): fragmentGoodsReceived {
            val f: fragmentGoodsReceived? = fragmentGoodsReceived()
            val b: Bundle= Bundle()
            b.putString("frmGR",text)
            f!!.arguments = b
            return f

        }
    }
}