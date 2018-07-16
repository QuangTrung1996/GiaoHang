package com.kltn.congphuc.giaohang.model

import android.util.Log
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Pushnotification
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Result
import com.kltn.congphuc.giaohang.dataRetrofit.respondRequestCancel
import com.kltn.congphuc.giaohang.retrofit.APIUtils
import com.kltn.congphuc.giaohang.retrofit.dataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class modelSendFCMConfirmDelivery(var respondSendFCMConfirmDelivery: respondSendFCMConfirmDelivery) {
    fun postRequetNo(invoiceId: String) {

        val api1: dataClient = APIUtils.postRequestConfirmDelivey()
        val call1: Call<respondRequestCancel> = api1.postRequetFCMConfirm(invoiceId)
        call1.enqueue(object : Callback<respondRequestCancel> {
            override fun onResponse(call: Call<respondRequestCancel>, response: Response<respondRequestCancel>) {
                if (response.isSuccessful) {
                    val invoice1: respondRequestCancel = response.body()!! as respondRequestCancel
                    if (invoice1.success == true) {
                        respondSendFCMConfirmDelivery.thanhcong()
                    } else {
                        respondSendFCMConfirmDelivery.thatbai()
                    }
                } else {
                    respondSendFCMConfirmDelivery.thatbai()
                }
            }

            override fun onFailure(call: Call<respondRequestCancel>, t: Throwable) {
                respondSendFCMConfirmDelivery.thatbai()
                Log.d("post", t.toString())

            }
        })

    }
}