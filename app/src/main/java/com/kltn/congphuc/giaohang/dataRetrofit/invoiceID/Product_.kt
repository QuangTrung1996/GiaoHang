package com.kltn.congphuc.giaohang.dataRetrofit.invoiceID

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Product_: Serializable {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("weight")
    @Expose
    var weight: String? = null
    @SerializedName("price")
    @Expose
    var price: String? = null

}