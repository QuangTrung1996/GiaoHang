package com.kltn.congphuc.giaohang.dataRetrofit
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
class AuthenticatedShipper {


    @SerializedName("_id")
    @Expose
     var id: String? = null
    @SerializedName("img")
    @Expose
    var img: String? = null
    @SerializedName("email")
    @Expose
     var email: String? = null
    @SerializedName("phone")
    @Expose
     var phone: String? = null
    @SerializedName("address")
    @Expose
     var address: String? = null
    @SerializedName("name")
    @Expose
     val name: String? = null
    @SerializedName("licensePlate")
    @Expose
    val licensePlate: String? = null
}