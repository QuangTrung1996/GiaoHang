package com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("authenticatedShipper")
    @Expose
    var authenticatedShipper: AuthenticatedShipper? = null

}