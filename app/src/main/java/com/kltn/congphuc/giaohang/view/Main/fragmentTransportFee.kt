package com.kltn.congphuc.giaohang.view.Main

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
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
import com.kltn.congphuc.giaohang.checkInternet.CheckInternet
import com.kltn.congphuc.giaohang.checkInternet.CheckInternetInterface
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.InvoiceID
import com.kltn.congphuc.giaohang.model.ConectDatabaseSQLite
import com.kltn.congphuc.giaohang.model.sharedPreferences
import com.kltn.congphuc.giaohang.presenter.presenterLoadVoiceID
import com.kltn.congphuc.giaohang.presenter.presenterSendRequetNo
import com.kltn.congphuc.giaohang.view.viewLoadInvoince
import com.kltn.congphuc.giaohang.view.viewLoadVoice
import org.jetbrains.annotations.Nullable
import java.util.*

/**
 * Created by congp on 3/18/2018.
 */
class fragmentTransportFee: Fragment(),viewLoadVoice,CheckInternetInterface {
    override fun kiemtrainternet(flag: Boolean) {
        if (flag)
        {
            if (idInvoice !=null) {
                val prstload: presenterLoadVoiceID = presenterLoadVoiceID(this, idInvoice!!)
                prstload.loadLoadVoiceID()
            }
        }
    }

    override fun loadThanhCong(invoiceID: InvoiceID) {
        listDonHang.add(DataTransportFee(invoiceID!!.data!!.invoice!!.id!!, invoiceID!!.data!!.invoice!!.price!!.toFloat()!!, invoiceID!!.data!!.invoice!!.orderDate!!))
        adapter.notifyDataSetChanged()
        lvTransportFree!!.adapter = adapter
        procesloadvoiceID!!.visibility = ProgressBar.INVISIBLE
        lvTransportFree!!.visibility = ListView.VISIBLE
    }

    override fun loadThatBai() {
        Toast.makeText(this.context,Toast.LENGTH_LONG,Toast.LENGTH_LONG).show()
    }
    private var idInvoice:String? = null
    private  var listDonHang :ArrayList<DataTransportFee> = ArrayList()
    private lateinit var adapter: TransporrtFeeAdapter
    private var lvTransportFree: ListView? =null
    private val DATABASENAME:String="donHang.sqlite"
    private var database: SQLiteDatabase?=null;
    private var procesloadvoiceID:ProgressBar?=null

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_transport_fee, container, false);
        lvTransportFree=view.findViewById(R.id.Lv_transport)as ListView
        procesloadvoiceID = view.findViewById(R.id.procesloadvoiceID)
        database = ConectDatabaseSQLite().initDatabase(this.activity,DATABASENAME);
        layTT()


       // listDonHang.add(DataTransportFee("sdafdssfdgtr",230000f,540f))
//        listDonHang.add(DataTransportFee("fđbdf",300000f,540f))
//        listDonHang.add(DataTransportFee("sdafdssfdgtr",13000f,40f))
//        listDonHang.add(DataTransportFee("dsvqerhsgffh",230000f,570f))
//        listDonHang.add(DataTransportFee("zxvsdgdvdsv",2156100f,50f))
        adapter = TransporrtFeeAdapter(this.context, listDonHang)
        //adapter.notifyDataSetChanged()
        //lvTransportFree!!.adapter = adapter
        return view

    }

    @SuppressLint("Recycle")
    private fun layTT() {
        val userInfor: sharedPreferences = sharedPreferences(this.context)
        val thongtin =userInfor.docThongTin()
        val userName = thongtin.get(3)
        val cursor : Cursor = database!!.rawQuery("SELECT * FROM delivered where delivered.idShipper LIKE '$userName%'",null)
        val calender: Calendar = Calendar.getInstance()
        val ngay = calender.get(Calendar.DAY_OF_MONTH)
        val thang = calender.get(Calendar.MONTH)
        val nam = calender.get(Calendar.YEAR)
        val ngaythang = (ngay.toString())+(thang.toString())+(nam.toString())
        //cursor.moveToPosition(2)
        Log.d("tong", cursor.count.toString())
        for (i in 0 until cursor.count){
            cursor.moveToPosition(i)
            if (cursor.getString(2) ==ngaythang) {
                val checkInternet = CheckInternet(this)
                idInvoice = cursor.getString(3)
                checkInternet.checkConnection(this.context)
            }
        }
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