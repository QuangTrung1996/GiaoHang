package com.kltn.congphuc.giaohang.Main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kltn.congphuc.giaohang.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.annotations.Nullable
import android.os.CountDownTimer
import android.util.Log
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import com.kltn.congphuc.giaohang.presenter.presenterLoadInvoince
import com.kltn.congphuc.giaohang.presenter.presenterSendRequetNo
import com.kltn.congphuc.giaohang.view.AdapterCallback
import com.kltn.congphuc.giaohang.view.viewLoadInvoince
import com.kltn.congphuc.giaohang.view.viewRespondNo


/**
 * Created by congp on 3/17/2018.
 */
class fragmentHome : Fragment(), AdapterCallback,viewLoadInvoince,viewRespondNo {
    override fun senThanhCong() {
        textviewanimation.visibility = TextView.VISIBLE
        val trans: Animation = AnimationUtils.loadAnimation(this.context,R.anim.translate_textview)
        textviewanimation.startAnimation(trans)
        object : CountDownTimer(2000, 200) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                textviewanimation.visibility = TextView.INVISIBLE
                tongKG.setText(tongKG.text.toString().plus(textviewanimation.text.toString()))

            }

        }.start()
//        val sharedPreferences = sharedPreferences(this.context)
//        val inforUser = sharedPreferences.docThongTin()
//        val id = inforUser.get(3)
//        val idinvoice ="5ac89717fbca612954896528"
}

    override fun senThatBai() {
        customToat("đơn hàng không tồn tại")
    }
    fun customToat(noidung:String){
        val layoutInflater:LayoutInflater = getLayoutInflater()
        val view:View = layoutInflater.inflate(R.layout.custom_toast,null)
        val txtToat:TextView = view.findViewById(R.id.TXTtoat)
        txtToat.text = noidung
        val toat:Toast = Toast(this.context)
        toat.view = view
        toat.duration = Toast.LENGTH_SHORT
        toat.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 150)
        toat.show()
    }
    override fun onMethodCallback() {
        val presenterSendRequetNo = presenterSendRequetNo(this)
        presenterSendRequetNo.send()
//        textviewanimation.visibility = TextView.VISIBLE
//        val trans: Animation = AnimationUtils.loadAnimation(this.context,R.anim.translate_textview)
//        textviewanimation.startAnimation(trans)
//        object : CountDownTimer(2000, 200) {
//            override fun onTick(p0: Long) {
//            }
//
//            override fun onFinish() {
//                textviewanimation.visibility = TextView.INVISIBLE
//                tongKG.setText(tongKG.text.toString().plus(textviewanimation.text.toString()))
//
//            }
//
//        }.start()
//        val sharedPreferences = sharedPreferences(this.context)
//        val inforUser = sharedPreferences.docThongTin()
//        val id = inforUser.get(3)
//        val idinvoice ="5ac89717fbca612954896528"
////        val mdpost = modelPostRequetNo()
////        mdpost.postRequetNo()
////        Toast.makeText(this.context,"cscsc",Toast.LENGTH_SHORT).show()
    }
    private  var listDonHang :ArrayList<HomeData> = ArrayList()
    private lateinit var adapter:HomeAdapter
    private var lvHome:ListView? =null
    private var loaprocess:ProgressBar?=null
    private var invoicesdata:InvoicesShiper = InvoicesShiper()

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_home, container, false);
        adapter = HomeAdapter(this.context,listDonHang,this)
        lvHome=view.findViewById(R.id.Lv_Home)as ListView
        lvHome!!.visibility = ListView.INVISIBLE
        loaprocess = view.findViewById(R.id.loadinvoice)
        loaprocess!!.visibility = ProgressBar.VISIBLE
        var prstload:presenterLoadInvoince = presenterLoadInvoince(this)
        prstload.loadVoice()


        return view
        fragmentHome
    }

    override fun loadThanhCong(invoicesShiper: InvoicesShiper) {
        super.loadThanhCong(invoicesShiper)
        //Log.d("fragmentHome","dc")
        loaprocess!!.visibility = ProgressBar.INVISIBLE
        invoicesdata=invoicesShiper
        docdata(invoicesShiper)
    }

    override fun ondetaill(position: Int) {
//        val invoice:Invoice = invoicesdata!!.data!!.invoices!!.get(position)
//        var data = datainvoiceSerializable(invoice!!.id!!,invoice.orderDate!!,invoice.paid!!,invoice.price!!,invoice.products!!,invoice.tasks!!,invoice.customer!!)
//        val intent = Intent(this.context,DetailBill::class.java)
//        intent.putExtra("data",data)
//        context.startActivity(intent)
//
    }
    private fun docdata(invoicesShiper: InvoicesShiper) {
        for (i in 0..invoicesShiper!!.data!!.authenticatedShipper!!.unPaidInvoices!!.size-1)
        {

//                listDonHang.add(HomeData("sdafdssfdgtr","Đỗ Công Phuc","117/134/7 phuong 22 nguyên huu cnah",
//                23,5400000f))
                //Log.d("testid",invoinces!!.data!!.invoices!!.size.toString())
                listDonHang.add(HomeData(invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i)!!.id!!,invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i).customer!!.name!!,invoicesShiper.data!!.authenticatedShipper!!.unPaidInvoices!!.get(0).tasks!!.location!!.address!!,10,invoicesShiper!!.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i)!!.price!!.toFloat()))


        }
        adapter.notifyDataSetChanged()
        lvHome!!.adapter = adapter
        lvHome!!.visibility = ListView.VISIBLE

    }

    override  fun loadthatbai() {
        super.loadthatbai()
        Log.d("fragmentHome","thất bại rồi")

    }

    companion object {

        @JvmStatic
        fun newInstance(text:String):fragmentHome{
            var f: fragmentHome? = fragmentHome()
            var b:Bundle?= Bundle()
            b!!.putString("frm",text)
            f!!.arguments = b
            return f!!

        }
    }

}
