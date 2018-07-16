package com.kltn.congphuc.giaohang.model

import android.util.Log
import com.kltn.congphuc.giaohang.dataRetrofit.respondRequestCancel
import com.kltn.congphuc.giaohang.retrofit.APIUtils
import com.kltn.congphuc.giaohang.retrofit.dataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class modelPostRequetCancel (var respondSendRquetPostCancel: respondSendRquetPostCancel,var idvoice:String,var idStore:String,var idUsser:String,var reason:String){
    fun postRequetCancel(){
        val api: dataClient = APIUtils.postRequestCancel()
        val call: Call<respondRequestCancel> = api.postRequestCancel(idvoice,idStore,idUsser,reason)
        call.enqueue(object : Callback<respondRequestCancel> {
            override fun onResponse(call: Call<respondRequestCancel>, response: Response<respondRequestCancel>) {
                if (response.isSuccessful) {
                    val invoice: respondRequestCancel = response.body()!! as respondRequestCancel
                    if (invoice!!.success == true) {
                        respondSendRquetPostCancel.sendThanhCong()
                        Log.d("post", "đã hủy đơn hàng")
                    } else {
                        respondSendRquetPostCancel.sendThatBai()
                        Log.d("post", "đơn hàng không tồn tại")
                    }
                }
                else
                {
                    respondSendRquetPostCancel.sendThatBai()
                    Log.d("post", "tại sao ko cap nhat dc" )
                }
            }

            override fun onFailure(call: Call<respondRequestCancel>, t: Throwable) {
                respondSendRquetPostCancel.sendThatBai()
                Log.d("post",t.toString())

            }
        })
    }
}