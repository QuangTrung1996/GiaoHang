package com.kltn.congphuc.giaohang.presenter

import com.kltn.congphuc.giaohang.model.modelPostRequetNo
import com.kltn.congphuc.giaohang.model.respondSendRquetNo
import com.kltn.congphuc.giaohang.view.viewRespondNo

class presenterSendRequetNo(var viewRespondNo: viewRespondNo, var idvoice:String, var idUsser:String,var time:Double):respondSendRquetNo {
    fun send(){
        val modelPostRequetNo = modelPostRequetNo(this,idvoice,idUsser,time)
        modelPostRequetNo.postRequetNo()
    }

    override fun sendThanhCong() {
        viewRespondNo.senThanhCong()
    }

    override fun sendThatBai() {
        viewRespondNo.senThatBai()
    }
}