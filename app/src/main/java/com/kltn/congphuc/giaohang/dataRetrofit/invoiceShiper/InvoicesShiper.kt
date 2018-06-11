package com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InvoicesShiper {

    @SerializedName("data")
    @Expose
    var data: Data? = null

}