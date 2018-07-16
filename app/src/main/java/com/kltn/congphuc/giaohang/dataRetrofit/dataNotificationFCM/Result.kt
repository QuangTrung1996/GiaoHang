package com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("multicast_id")
    @Expose
    var multicastId: Double? = null
    @SerializedName("success")
    @Expose
    var success: Int? = null
    @SerializedName("failure")
    @Expose
    var failure: Int? = null
    @SerializedName("canonical_ids")
    @Expose
    var canonicalIds: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<Result_>? = null

}