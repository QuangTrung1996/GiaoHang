package com.kltn.congphuc.giaohang.presenter

import com.kltn.congphuc.giaohang.dataRetrofit.User
import com.kltn.congphuc.giaohang.model.modelLogin
import com.kltn.congphuc.giaohang.model.responeLogin
import com.kltn.congphuc.giaohang.view.viewLogin

class presenterLogin(var viewLogin: viewLogin):responeLogin {
    fun chuyendangnhap(tenDangNhap:String, matKhau:String){
        val modelLogin = modelLogin(this)
        modelLogin.kiemTraDangNhap(tenDangNhap,matKhau)
    }

    override fun loginThanhCong(userInfor: User) {
        super.loginThanhCong(userInfor)
        viewLogin.thanhCong(userInfor)
    }

    override fun loginThatBai() {
        super.loginThatBai()
        viewLogin.thatBai()
    }

    override fun thieudulieu(a: Int) {
        super.thieudulieu(a)
        viewLogin.thieudulieu(a)
    }

}