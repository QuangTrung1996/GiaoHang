package com.kltn.congphuc.giaohang.presenter

import com.kltn.congphuc.giaohang.model.modelPostRequetCancel
import com.kltn.congphuc.giaohang.model.modelPostRequetNo
import com.kltn.congphuc.giaohang.model.respondSendRquetPostCancel
import com.kltn.congphuc.giaohang.view.viewCancelInvoice

class presenterSeenRequetCancelInvoice(var viewCancelInvoice:viewCancelInvoice, var idInvoice:String, var idStore:String, var isUsser:String, var reason:String):respondSendRquetPostCancel {
    override fun sendThanhCong() {
        viewCancelInvoice.huyThanhCong()
    }

    override fun sendThatBai() {
        viewCancelInvoice.huyThatBai()
    }

    fun postCancelInvoice()
    {
        val modelPostRequetCancel = modelPostRequetCancel(this,idInvoice,idStore,isUsser,reason)
        modelPostRequetCancel.postRequetCancel()
    }
}