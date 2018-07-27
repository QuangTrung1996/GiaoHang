package com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class nhiemVu: Serializable {

    @SerializedName("estimationTime")
    @Expose
    var estimationTime: String? = null
    @SerializedName("location")
    @Expose
    var location: Location? = null

}