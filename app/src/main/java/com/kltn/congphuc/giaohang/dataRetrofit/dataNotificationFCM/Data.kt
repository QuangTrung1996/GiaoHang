package com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data ( title:String, body:String){

    @SerializedName("title")
    @Expose
    var title: String? = title
    @SerializedName("body")
    @Expose
    var body: String? = body

}