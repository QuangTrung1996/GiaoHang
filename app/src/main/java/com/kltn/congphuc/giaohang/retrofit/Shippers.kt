package com.kltn.congphuc.giaohang.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Shippers {

    @SerializedName("data")
    @Expose
    var data: Data? = null
}