package com.kltn.congphuc.giaohang.dataRetrofit.invoiceID

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class InvoiceID : Serializable {

    @SerializedName("data")
    @Expose
    var data: Data? = null

}