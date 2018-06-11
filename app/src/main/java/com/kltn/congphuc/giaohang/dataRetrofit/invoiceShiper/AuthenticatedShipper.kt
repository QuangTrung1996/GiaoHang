package com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthenticatedShipper {

//    @SerializedName("_id")
//    @Expose
//    var id: String? = null
    @SerializedName("unPaidInvoices")
    @Expose
    var unPaidInvoices: List<UnPaidInvoice>? = null

}