package com.kltn.congphuc.giaohang.view.statistics

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.checkInternet.CheckInternet
import com.kltn.congphuc.giaohang.checkInternet.CheckInternetInterface
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.Invoice
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.InvoiceID
import com.kltn.congphuc.giaohang.model.ConectDatabaseSQLite
import com.kltn.congphuc.giaohang.presenter.presenterLoadVoiceID
import com.kltn.congphuc.giaohang.view.Main.TransporrtFeeAdapter
import com.kltn.congphuc.giaohang.view.viewLoadVoice
import kotlinx.android.synthetic.main.row_statistict_header.*
import org.jetbrains.annotations.Nullable
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class fragmentStatistics : Fragment(),CheckInternetInterface , viewLoadVoice {
    private var invoice:Invoice? = Invoice()
    override fun loadThanhCong(invoiceID: InvoiceID) {
        layTT(invoiceID)
       // Toast.makeText(this.context,"thanhcong",Toast.LENGTH_SHORT).show()
    }

    private fun layTT(invoiceID: InvoiceID) {
        Arraychild!!.add(dataDetailStatistics(invoiceID.data!!.invoice!!.id!!,invoiceID.data!!.invoice!!.price.toString().plus(".000 đ"),invoiceID.data!!.invoice!!.orderDate!!))
        for (i in 0 until ArrayHeader!!.size) {
            var arraychild: ArrayList<dataDetailStatistics>? = ArrayList()
            arrayChild!!.clear()
            for (j in 0 until ArrayStempt!!.size) {
                if (ArrayStempt!!.get(j).ngay == ArrayHeader!!.get(i).ngay) {

                    for (k in 0 until Arraychild!!.size) {
                        if (ArrayStempt!!.get(j).id == Arraychild!!.get(k).id) {
                            arrayChild!!.add(Arraychild!![k])
                            break
                        }
                    }
                }
                arraychild = arrayChild
                Log.d("deohieu",Arraychild!!.size.toString())

                HasMap!!.put(ArrayHeader!![i], arraychild!!)
            }

        }
        if (HasMap!!.size >0)
        {
            Log.d("deohieu","deohieu")
        }
        adapter!!.notifyDataSetChanged();
        ExpandbleLV!!.setAdapter(adapter)
        chuacodonhang!!.visibility = TextView.INVISIBLE
        ExpandbleLV!!.visibility = ExpandableListView.VISIBLE    }

    override fun loadThatBai() {
        Log.d("thatbai","that bai")
    }

    override fun kiemtrainternet(flag: Boolean) {
        if (flag)
        {
            if (idInvoice !=null) {
                val prstload: presenterLoadVoiceID = presenterLoadVoiceID(this, idInvoice!!)
                prstload.loadLoadVoiceID()
            }
            else
                Log.d("thatbai","that bai")

        }
    }
    private var arrayChild:ArrayList<dataDetailStatistics>?= ArrayList()
    private var ArrayStempt : ArrayList<dataDetailStatistics>?= ArrayList()
    private var Arraychild:ArrayList<dataDetailStatistics>?= ArrayList();
    private var HasMap:HashMap<statisticsNgay,ArrayList<dataDetailStatistics>>?= HashMap()
    private var ArrayHeader : ArrayList<statisticsNgay>?= ArrayList()
    private val DATABASENAME:String="donHang.sqlite"
    private var database: SQLiteDatabase?=null;
    private var ExpandbleLV: ExpandableListView?=null
    private lateinit var expdStatistics: expdStatistics
    private var idInvoice:String? = null
    private var chuacodonhang:TextView?=null
    private var adapter:expdStatistics ?=null
    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_statistics, container, false);
        chuacodonhang = view.findViewById(R.id.chuacodonhang)
        ExpandbleLV = view.findViewById(R.id.expdStatistics)
        expdStatistics = expdStatistics(this.context,ArrayHeader!!, HasMap!!)
        database = ConectDatabaseSQLite().initDatabase(this.activity,DATABASENAME);
        adapter= expdStatistics(this.activity,ArrayHeader!!,HasMap!!)
        docThongTinSQL()
        //addThongTin()
        for (i in 0 until ArrayStempt!!.size)
        {
            val prstload: presenterLoadVoiceID = presenterLoadVoiceID(this, idInvoice!!)
            prstload.loadLoadVoiceID()
        }
        adapter!!.notifyDataSetChanged();
        ExpandbleLV!!.setAdapter(adapter)
        chuacodonhang!!.visibility = TextView.INVISIBLE
        ExpandbleLV!!.visibility = ExpandableListView.VISIBLE
        return view
    }

    private fun addThongTin() {
        if (Arraychild!!.size >0) {
            for (i in 0 until ArrayHeader!!.size) {
                var arraychild: ArrayList<dataDetailStatistics>? = ArrayList()
                arrayChild!!.clear()
                for (j in 0 until ArrayStempt!!.size) {
                    if (ArrayStempt!!.get(j).ngay == ArrayHeader!!.get(i).ngay) {

                        for (k in 0 until Arraychild!!.size) {
                            if (ArrayStempt!!.get(j).id == Arraychild!!.get(k).id) {
                                arrayChild!!.add(Arraychild!![k])
                                break
                            }
                        }
                    }
                    arraychild = arrayChild
                    HasMap!!.put(ArrayHeader!![i], arraychild!!)
                }

            }
            adapter!!.notifyDataSetChanged();
            ExpandbleLV!!.setAdapter(adapter)
            chuacodonhang!!.visibility = TextView.INVISIBLE
            ExpandbleLV!!.visibility = ExpandableListView.VISIBLE
        }
        else
        {
            Log.d("deohieu","deohieu")
        }
    }

    private fun docThongTinSQL() {
        val cursor : Cursor = database!!.rawQuery("SELECT * FROM delivered",null)
        val calender: Calendar = Calendar.getInstance()
        val ngay = calender.get(Calendar.DAY_OF_MONTH)
        val thang = calender.get(Calendar.MONTH)
        val nam = calender.get(Calendar.YEAR)
        //cursor.moveToPosition(2)
        var a=0;
        for (i in 0 until cursor.count){
            cursor.moveToPosition(i)
            if (i==0)
            {
                val string = cursor.getString(2)
                val ng :String=  string.substring(0,2)
                val th = string.substring(2,4)
                val n  = string.substring(3)

                ArrayHeader!!.add(statisticsNgay(ng.plus("/")+th.plus("/")+n))
//                var arraychild:ArrayList<dataDetailStatistics>?= ArrayList()
//                arraychild = docThongTinSQLChild(cursor.getString(2))
                docThongTinSQLChild(cursor.getString(2))

//                Arraychild= ArrayList()
//                Arraychild = docThongTinSQLChild(cursor.getString(2))
//                HasMap!!.put(ArrayHeader!![a],Arraychild!!)
                a++

            }

            else
            {
                var flat =true
                for (y in 0 until ArrayHeader!!.size)
                {
                    if (cursor.getString(2) == ArrayHeader!!.get(y).ngay)
                    {
                        flat = false
                        break
                    }
                }
                if (flat)
                {
                    val string = cursor.getString(2)
                    val ng :String=  string.substring(0,1)
                    val th = string.substring(2,3)
                    val n  = string.substring(3)

                    ArrayHeader!!.add(statisticsNgay(ng.plus("/")+th.plus("/")+n))//                    var arraychild:ArrayList<dataDetailStatistics>?= ArrayList()
//                    arraychild =
                        docThongTinSQLChild(cursor.getString(2))

//                    Arraychild= ArrayList()
//                    Arraychild = docThongTinSQLChild(cursor.getString(2))
//                    HasMap!!.put(ArrayHeader!![a],Arraychild!!)
                    a++
                }
            }
        }

    }

    private fun docThongTinSQLChild(string: String?):ArrayList<dataDetailStatistics> {
        Arraychild!!.clear()
        val cursor : Cursor = database!!.rawQuery("SELECT * FROM delivered where delivered.date = '$string'",null)
        if (cursor.count >0)
        {
            for (i in 0 until cursor.count){
                cursor.moveToPosition(i)
                idInvoice = cursor.getString(3)
                ArrayStempt!!.add(dataDetailStatistics(idInvoice!!,cursor.getString(2),"n"))
//                val checkInternet = CheckInternet(this)
//                checkInternet.checkConnection(this.context)


            }
        }
        return Arraychild!!
    }


}