package com.kltn.congphuc.giaohang.dataRetrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class respondRequestCancel {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null
}