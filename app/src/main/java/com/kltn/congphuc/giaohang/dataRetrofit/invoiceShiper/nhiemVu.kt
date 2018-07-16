package com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class nhiemVu: Serializable {

    @SerializedName("receipt_date")
    @Expose
    var receiptDate: String? = null
    @SerializedName("location")
    @Expose
    var location: Location? = null

}