package com.kltn.congphuc.giaohang.dataRetrofit
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Data {


    @SerializedName("authenticatedShipper")
    @Expose
    var authenticatedShipper: AuthenticatedShipper? = null

//    fun getAuthenticatedShipper(): AuthenticatedShipper? {
//        return authenticatedShipper
//    }
//
//    fun setAuthenticatedShipper(authenticatedShipper: AuthenticatedShipper) {
//        this.authenticatedShipper = authenticatedShipper
//    }

}