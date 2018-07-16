package com.kltn.congphuc.giaohang.dataRetrofit.Store

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class thongTinCuaHang {

    @SerializedName("data")
    @Expose
    var data: Data? = null

}