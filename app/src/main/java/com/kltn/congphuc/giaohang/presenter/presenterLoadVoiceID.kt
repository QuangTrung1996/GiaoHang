package com.kltn.congphuc.giaohang.presenter

import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.InvoiceID
import com.kltn.congphuc.giaohang.model.modleLoadVoiceID
import com.kltn.congphuc.giaohang.model.respondLoadVoiceID
import com.kltn.congphuc.giaohang.view.viewLoadInvoince
import com.kltn.congphuc.giaohang.view.viewLoadVoice

class presenterLoadVoiceID(var viewLoadVoice: viewLoadVoice, var idVoice:String):respondLoadVoiceID{
    override fun loadThanhCong(invoiceID: InvoiceID) {
        viewLoadVoice.loadThanhCong(invoiceID)
    }

    override fun loadThatBai() {
        viewLoadVoice.loadThatBai()
    }

    fun loadLoadVoiceID(){
        val modleLoadVoiceID = modleLoadVoiceID(this,idVoice)
        modleLoadVoiceID.loadDataInvoices()
    }
}