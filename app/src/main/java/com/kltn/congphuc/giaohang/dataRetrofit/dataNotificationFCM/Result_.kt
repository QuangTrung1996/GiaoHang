package com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result_ {

    @SerializedName("message_id")
    @Expose
    var messageId: String? = null

}