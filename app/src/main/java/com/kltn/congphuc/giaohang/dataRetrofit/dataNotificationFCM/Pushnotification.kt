package com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pushnotification( to:String,notification:Notification, data:Data) {

    @SerializedName("to")
    @Expose
    var to: String? = to
    @SerializedName("notification")
    @Expose
    var notification: Notification? = notification
    @SerializedName("data")
    @Expose
    var data: Data? = data

}