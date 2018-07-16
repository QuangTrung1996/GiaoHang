package com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UnPaidInvoice:Serializable {

    @SerializedName("paid")
    @Expose
    var paid: Boolean? = null
    @SerializedName("_id")
    @Expose
    var id: String? = null
    @SerializedName("order_date")
    @Expose
    var orderDate: String? = null
    @SerializedName("price")
    @Expose
    var price: Int? = null
    @SerializedName("tasks")
    @Expose
    var tasks: nhiemVu? = null
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