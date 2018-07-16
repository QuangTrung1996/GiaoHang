package com.kltn.congphuc.giaohang.dataRetrofit.Store

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Store {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("location")
    @Expose
    var location: Location? = null
    @SerializedName("owner")
    @Expose
    var owner: Owner? = null
    @SerializedName("shippers")
    @Expose
    var shippers: List<Shipper>? = null

}
