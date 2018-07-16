package com.kltn.congphuc.giaohang.model

import android.util.Log
import com.google.gson.JsonObject
import com.kltn.congphuc.giaohang.dataRetrofit.PostRequestNo
import com.kltn.congphuc.giaohang.dataRetrofit.respondRequestCancel
import com.kltn.congphuc.giaohang.retrofit.APIUtils
import com.kltn.congphuc.giaohang.retrofit.dataClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class modelUpdateInforUser(var respondUdateInforUser: respondUdateInforUser,var json: JsonObject) {
    fun postRequetNo(){
        Log.d("update",json.toString())
        val api: dataClient = APIUtils.postUpdateUser()
        val call: Call<respondRequestCancel> = api.postUpdateUser(json)
        call.enqueue(object : Callback<respondRequestCancel> {
            override fun onResponse(call: Call<respondRequestCancel>, response: Response<respondRequestCancel>) {
                if (response.isSuccessful) {
                    val invoice: respondRequestCancel = response.body()!! as respondRequestCancel
                    if (invoice!!.success == true) {
                        respondUdateInforUser.updateThanhCong()
                    } else {
                        respondUdateInforUser.updateThatBai()

                    }
                }
                else
                    respondUdateInforUser.updateThatBai()


            }

            override fun onFailure(call: Call<respondRequestCancel>, t: Throwable) {
                respondUdateInforUser.updateThatBai()

            }
        })
    }
}