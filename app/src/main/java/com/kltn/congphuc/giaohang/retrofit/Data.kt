package com.kltn.congphuc.giaohang.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("shippers")
    @Expose
    var shippers: ArrayList<Shipper>? = null



}