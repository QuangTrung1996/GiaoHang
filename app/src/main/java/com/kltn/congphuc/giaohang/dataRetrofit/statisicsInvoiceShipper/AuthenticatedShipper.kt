package com.kltn.congphuc.giaohang.dataRetrofit.statisicsInvoiceShipper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthenticatedShipper {

    @SerializedName("invoices")
    @Expose
    var invoices: List<Invoice>? = null

}