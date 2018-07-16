package com.kltn.congphuc.giaohang.dataRetrofit.Store

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Shipper {

    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("phone")
    @Expose
    var phone: String? = null
    @SerializedName("img")
    @Expose
    var img: String? = null

}