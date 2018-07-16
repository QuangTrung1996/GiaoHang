package com.kltn.congphuc.giaohang.dataRetrofit.invoiceID

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Product : Serializable {

    @SerializedName("product")
    @Expose
    var product: Product_? = null
    @SerializedName("quantity")
    @Expose
    var quantity: Int? = null

}