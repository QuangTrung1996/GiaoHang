package com.kltn.congphuc.giaohang.model

import android.util.Log
import android.widget.Toast
import com.kltn.congphuc.giaohang.dataRetrofit.User
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import com.kltn.congphuc.giaohang.retrofit.APIUtils
import com.kltn.congphuc.giaohang.retrofit.dataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class modelLoadInvoices(var tenDangNhap:String,var matKhau:String, var responelloadVoinces: responelLoadVoinces) {
    fun loadDataInvoices(){
        val api: dataClient = APIUtils.getdata()
        val query:String = "{authenticatedShipper(email:\"".plus(""+tenDangNhap+"\",".plus("pass:\""+matKhau+"\")"))+"{_id,unPaidInvoices{ _id order_date price tasks{  location{ address lat lng } } products{ product{ weight price name img } quantity } customer{ name phone token } store{ _id location{ address lat lng } } } } }"
        val call: Call<InvoicesShiper> = api.getDataInvoice(query)
        call.enqueue(object : Callback<InvoicesShiper> {
            override fun onResponse(call: Call<InvoicesShiper>, response: Response<InvoicesShiper>) {
                if (response.isSuccessful) {
                    val invoice: InvoicesShiper = response.body()!! as InvoicesShiper
                    if (invoice.data!!.authenticatedShipper!!.unPaidInvoices!!.isNotEmpty() ) {
//                        val a = user.data!!.authenticatedShipper!!.email
//                       Log.d("testname", invoice.data!!.invoices!!.get(0).customer!!.name)
                        responelloadVoinces.loadthanhcong(invoice)
//                        Log.d("loadinvoice", invoice.data!!.authenticatedShipper!!.unPaidInvoices!!.get(0).orderDate)
                    } else {
                        Log.d("loadinvoice", "sao kỳ vậy ta")
                        responelloadVoinces.loadthatbai()
                    }

                }
                else
                {
                    responelloadVoinces.loadthatbai()
                }
            }


            override fun onFailure(call: Call<InvoicesShiper>, t: Throwable) {
                Log.d("loadinvoice",t.toString())
                responelloadVoinces.loadthatbai()

            }
        })
    }
}