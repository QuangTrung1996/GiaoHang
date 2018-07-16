package com.kltn.congphuc.giaohang.dataRetrofit.invoiceID

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Customer :Serializable {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("phone")
    @Expose
    var phone: String? = null
    @SerializedName("_id")
    @Expose
    var id: String? = null

}