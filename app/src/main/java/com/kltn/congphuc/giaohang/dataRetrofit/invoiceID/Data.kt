package com.kltn.congphuc.giaohang.dataRetrofit.invoiceID

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("invoice")
    @Expose
    var invoice: Invoice? = null

}