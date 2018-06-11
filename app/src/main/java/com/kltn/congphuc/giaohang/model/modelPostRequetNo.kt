package com.kltn.congphuc.giaohang.model

import android.util.Log
import com.kltn.congphuc.giaohang.dataRetrofit.PostRequestNo
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import com.kltn.congphuc.giaohang.retrofit.APIUtils
import com.kltn.congphuc.giaohang.retrofit.dataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class modelPostRequetNo(var respondSendRquetNo: respondSendRquetNo) {
    fun postRequetNo(){
        val api: dataClient = APIUtils.postRequet()
        val call: Call<PostRequestNo> = api.postRequet("5b10df97cd3ca600140d6e02","5ac9704671d56b2cbc6cbfff",5661651.0)
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