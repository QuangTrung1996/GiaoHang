package com.kltn.congphuc.giaohang.dataRetrofit.Store

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthenticatedShipper {

    @SerializedName("store")
    @Expose
    var store: Store? = null

}