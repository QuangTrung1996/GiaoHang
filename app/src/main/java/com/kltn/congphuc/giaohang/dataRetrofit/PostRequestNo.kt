package com.kltn.congphuc.giaohang.dataRetrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostRequestNo {

    @SerializedName("success")
    @Expose
    var success: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null

}