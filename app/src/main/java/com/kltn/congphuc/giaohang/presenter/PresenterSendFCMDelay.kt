package com.kltn.congphuc.giaohang.presenter

import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Pushnotification
import com.kltn.congphuc.giaohang.model.modelSendFCMDelay
import com.kltn.congphuc.giaohang.model.respondSenFCMDelay
import com.kltn.congphuc.giaohang.view.viewDelay

class PresenterSendFCMDelay(var notifi:Pushnotification, var viewDelay: viewDelay): respondSenFCMDelay {
    fun sendNotifiFCM(){
        val sendModelFCMDelay = modelSendFCMDelay( this)
        sendModelFCMDelay.postRequetNo(notifi)
    }
    override fun sendThanhCong() {
        viewDelay.senThanhCong()
    }

    override fun sendThatBai() {
        viewDelay.senThatBai()
    }
}