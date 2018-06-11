package com.kltn.congphuc.giaohang.view

import com.kltn.congphuc.giaohang.dataRetrofit.User

interface viewLogin {
    fun thanhCong(userInfor:User){
    }
    fun thatBai(){
    }
    fun thieudulieu(a:Int){}
}