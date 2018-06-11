package com.kltn.congphuc.giaohang.presenter

import com.kltn.congphuc.giaohang.model.modelPostRequetNo
import com.kltn.congphuc.giaohang.model.respondSendRquetNo
import com.kltn.congphuc.giaohang.view.viewRespondNo

class presenterSendRequetNo(var viewRespondNo: viewRespondNo):respondSendRquetNo {
    fun send(){
        val modelPostRequetNo = modelPostRequetNo(this)
        modelPostRequetNo.postRequetNo()
    }

    override fun sendThanhCong() {
        viewRespondNo.senThanhCong()
    }

    override fun sendThatBai() {
        viewRespondNo.senThatBai()
    }
}