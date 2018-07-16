package com.kltn.congphuc.giaohang.dataRetrofit.statisicsInvoiceShipper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Invoice {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("paid")
    @Expose
    var paid: Boolean? = null
    @SerializedName("price")
    @Expose
    var price: Int? = null
    @SerializedName("order_date")
    @Expose
    var orderDate: String? = null

}