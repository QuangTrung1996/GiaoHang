package com.kltn.congphuc.giaohang.model

import android.util.Log
import com.kltn.congphuc.giaohang.dataRetrofit.PostRequestNo
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import com.kltn.congphuc.giaohang.retrofit.APIUtils
import com.kltn.congphuc.giaohang.retrofit.dataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class modelPostRequetNo(var respondSendRquetNo: respondSendRquetNo,var idvoice:String,var idUsser:String,var time:Double) {
    fun postRequetNo(){
        val api: dataClient = APIUtils.postRequet()
        val call: Call<PostRequestNo> = api.postRequet(idvoice,idUsser,time)
        call.enqueue(object : Callback<PostRequestNo> {
            override fun onResponse(call: Call<PostRequestNo>, response: Response<PostRequestNo>) {
                val invoice: PostRequestNo = response.body()!! as PostRequestNo
                if (invoice!!.success ==true) {
                    respondSendRquetNo.sendThanhCong()
                    Log.d("post","đã nhận đơn hàng")
                }
                else
                {
                    respondSendRquetNo.sendThatBai()
                    Log.d("post", "đơn hàng không tồn tại")
                }

            }

            override fun onFailure(call: Call<PostRequestNo>, t: Throwable) {
                respondSendRquetNo.sendThatBai()
                Log.d("post",t.toString())

            }
        })
    }
}