package com.kltn.congphuc.giaohang.view

interface callBackGoodsRecieved {
    fun callCancel(position: Int)
    fun callDelay(token:String){}
    fun callCallPhoneNumbe(position: Int){}
    fun callDetail(position: Int)
    fun callConfirmDelivery(position:Int)
}