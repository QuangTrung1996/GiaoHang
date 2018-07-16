package com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Product_ : Serializable {
    @SerializedName("weight")
    @Expose
    var weight: Double? = null
    @SerializedName("price")
    @Expose
    var price: Double? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("img")
    @Expose
    var img: String? = null
}