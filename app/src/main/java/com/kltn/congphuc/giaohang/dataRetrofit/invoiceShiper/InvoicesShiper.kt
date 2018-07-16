package com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class InvoicesShiper : Serializable {

    @SerializedName("data")
    @Expose
    var data: Data? = null

}