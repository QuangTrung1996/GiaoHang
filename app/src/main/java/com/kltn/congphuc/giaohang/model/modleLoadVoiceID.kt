package com.kltn.congphuc.giaohang.model

import android.util.Log
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.InvoiceID
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import com.kltn.congphuc.giaohang.retrofit.APIUtils
import com.kltn.congphuc.giaohang.retrofit.dataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class modleLoadVoiceID(var respondLoadVoiceID: respondLoadVoiceID, var idVoice:String) {
    fun loadDataInvoices(){
        val api: dataClient = APIUtils.getdata()
        val query:String = "{ invoice(id:\"".plus(idVoice).plus("\") { _id order_date price tasks { location{ address lat lng } } products{ product{ name  price weight } quantity } customer { name phone _id } store{ location{ address lat lng } } } }")
        Log.d("modelloadvoice",query)
        val call: Call<InvoiceID> = api.getVoiceID(query)
        call.enqueue(object : Callback<InvoiceID> {
            override fun onResponse(call: Call<InvoiceID>, response: Response<InvoiceID>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val invoice: InvoiceID = response.body()!! as InvoiceID
                        if (invoice!!.data != null) {
//                        val a = user.data!!.authenticatedShipper!!.email
//                       Log.d("testname", invoice.data!!.invoices!!.get(0).customer!!.name)
                            respondLoadVoiceID.loadThanhCong(invoice)
                            Log.d("loadinvoice", invoice.data!!.invoice!!.id)
                        } else {
                            Log.d("loadinvoice", "sao kỳ vậy ta")
                            respondLoadVoiceID.loadThatBai()
                        }

                    } else {
                        respondLoadVoiceID.loadThatBai()
                    }
                }
            }
            override fun onFailure(call: Call<InvoiceID>, t: Throwable) {
                Log.d("loadinvoice",t.toString())
                respondLoadVoiceID.loadThatBai()

            }
        })
    }
}