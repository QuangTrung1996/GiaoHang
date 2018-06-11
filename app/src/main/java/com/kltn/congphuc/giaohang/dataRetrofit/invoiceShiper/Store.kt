package com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Store {

    @SerializedName("location")
    @Expose
    var location: Location_? = null

}