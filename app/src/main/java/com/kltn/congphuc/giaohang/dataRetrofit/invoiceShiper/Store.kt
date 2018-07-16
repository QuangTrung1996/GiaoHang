package com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Store : Serializable {
    @SerializedName("_id")
    @Expose
    var  id: String? = null
    @SerializedName("location")
    @Expose
    var location: Location_? = null

}