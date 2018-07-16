package com.kltn.congphuc.giaohang.dataRetrofit.statisicsInvoiceShipper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("data")
    @Expose
    var data: Data? = null

}