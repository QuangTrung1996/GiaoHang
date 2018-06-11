package com.kltn.congphuc.giaohang.model

import com.kltn.congphuc.giaohang.dataRetrofit.User

interface responeLogin {
    fun loginThanhCong(userInfor:User){
    }
    fun loginThatBai(){
    }
    fun thieudulieu(a:Int){}
}