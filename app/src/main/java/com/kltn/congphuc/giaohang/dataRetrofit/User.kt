package com.kltn.congphuc.giaohang.dataRetrofit
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
class User {


    @SerializedName("data")
    @Expose
     var data: Data? = null

//    fun getData(): Data? {
//        return data
//    }
//
//    fun setData(data: Data) {
//        this.data = data
//    }
}