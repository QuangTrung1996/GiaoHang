package com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Product {

    @SerializedName("product")
    @Expose
    var product: Product_? = null
    @SerializedName("quantity")
    @Expose
    var quantity: Int? = null

}