package com.kltn.congphuc.giaohang.model

import android.util.Log
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Pushnotification
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Result
import com.kltn.congphuc.giaohang.retrofit.APIUtils
import com.kltn.congphuc.giaohang.retrofit.dataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class modelSendFCMDelay(var respondSenFCMDelay: respondSenFCMDelay) {
    fun postRequetNo(PushNTF:Pushnotification){
        val api: dataClient = APIUtils.sendNotiFiCaTiOn()
        val call: Call<Result> = api.senNotification(PushNTF)
        call.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val invoice: Result = response.body()!! as Result
                if (invoice!!.success!=null && invoice!!.success==1) {
                    respondSenFCMDelay.sendThanhCong()
                }
                else
                {
                    respondSenFCMDelay.sendThatBai()
                }

            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                respondSenFCMDelay.sendThatBai()

            }
        })
    }
}