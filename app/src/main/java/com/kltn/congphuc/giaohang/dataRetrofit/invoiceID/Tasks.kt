package com.kltn.congphuc.giaohang.dataRetrofit.invoiceID

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Tasks: Serializable {


    @SerializedName("location")
    @Expose
    var location: Location? = null

}
