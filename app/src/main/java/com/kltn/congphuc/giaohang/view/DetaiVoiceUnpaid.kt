package com.kltn.congphuc.giaohang.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.dataRetrofit.dataRecieveRiceType
import com.kltn.congphuc.giaohang.dataRetrofit.datainvoiceSerializable
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.UnPaidInvoice
import kotlinx.android.synthetic.main.activity_detai_voice_unpaid.*

class DetaiVoiceUnpaid : AppCompatActivity() {
    private var invoive:UnPaidInvoice? = null
    private lateinit var adapter:detailRecieveRiceTypeAdapter
    private var list:ArrayList<dataRecieveRiceType> ? =ArrayList()
    private var tongKL:Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detai_voice_unpaid)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        adapter = detailRecieveRiceTypeAdapter(this,list!!)
        val intent = intent
        invoive = intent.getSerializableExtra("data") as UnPaidInvoice?
        val name = findViewById<TextView>(R.id.tennguoinhandetailRecieve)
        docTT()
        name.text = invoive!!.customer!!.name
        iddonhangdetailRecieve.text = invoive!!.id
        diachinguoinhandetailRecieve.text = invoive!!.tasks!!.location!!.address
        sotiennhandetailRecieve.text = invoive!!.price.toString().plus(".000 đ")
        sodienthoaidetailRecieve.text = invoive!!.customer!!.phone
        khoiluongdetailRecieve.text = tongKL.toString()
    }

    private fun docTT() {
        for (i in invoive!!.products!! )
        {
            tongKL+= i.product!!.weight!!*i.quantity!!
           val dataRecieveRiceType = dataRecieveRiceType(i!!.product!!.name!!,i.quantity.toString(),i.product!!.weight!!.toString(),i.product!!.price!!.toString(),i.product!!.img!!)
            list!!.add(dataRecieveRiceType)
        }
        adapter.notifyDataSetChanged()
        LvDetailRecieve.adapter = adapter
        setListViewHeightBasedOnChildren(LvDetailRecieve)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        when (id) {
            android.R.id.home ->{
                finish()
            }

        }
        return true
    }

    fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter = listView.adapter ?: return

        val desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.UNSPECIFIED)
        var totalHeight = 0
        var view: View? = null
        for (i in 0 until listAdapter.count) {
            view = listAdapter.getView(i, view, listView)
            if (i == 0)
                view!!.layoutParams = ViewGroup.LayoutParams(desiredWidth, ViewPager.LayoutParams.WRAP_CONTENT)

            view!!.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += view.measuredHeight
        }
        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
        listView.layoutParams = params
    }
}
