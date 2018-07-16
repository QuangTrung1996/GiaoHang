package com.kltn.congphuc.giaohang.presenter

import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Pushnotification
import com.kltn.congphuc.giaohang.model.modelSendFCMConfirmDelivery
import com.kltn.congphuc.giaohang.model.respondSendFCMConfirmDelivery
import com.kltn.congphuc.giaohang.view.viewConfirmDeliver
import com.kltn.congphuc.giaohang.view.viewDelay

class presenterConfirmDelivery( var viewConfirmDeliver: viewConfirmDeliver, var idvoice:String):respondSendFCMConfirmDelivery {

    fun sendRequest()
    {
        val send:modelSendFCMConfirmDelivery = modelSendFCMConfirmDelivery(this)
        send.postRequetNo(idvoice)
    }
    override fun thanhcong() {
        viewConfirmDeliver.xacNhanThanhCong()
    }

    override fun thatbai() {
        viewConfirmDeliver.xacNhanThatBai()
    }
}