package com.kltn.congphuc.giaohang.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Shipper {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("pass")
    @Expose
    var pass: String? = null

}