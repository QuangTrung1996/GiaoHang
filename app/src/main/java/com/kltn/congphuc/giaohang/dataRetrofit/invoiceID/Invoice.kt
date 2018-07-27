package com.kltn.congphuc.giaohang.dataRetrofit.invoiceID

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Invoice: Serializable {

    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("order_date")
    @Expose
    var orderDate: String? = null
    @SerializedName("price")
    @Expose
    var price: Double? = null
    @SerializedName("tasks")
    @Expose
    var tasks: Tasks? = null
    @SerializedName("products")
    @Expose
    var products: List<Product>? = null
    @SerializedName("customer")
    @Expose
    var customer: Customer? = null
    @SerializedName("store")
    @Expose
    var store: Store? = null

}