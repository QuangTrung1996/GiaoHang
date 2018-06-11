package com.kltn.congphuc.giaohang.presenter

import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import com.kltn.congphuc.giaohang.model.modelLoadInvoices
import com.kltn.congphuc.giaohang.model.modelLogin
import com.kltn.congphuc.giaohang.model.responelLoadVoinces
import com.kltn.congphuc.giaohang.view.viewLoadInvoince

class presenterLoadInvoince(var viewLoadInvoince: viewLoadInvoince):responelLoadVoinces {

    fun loadVoice(){
        val modelLoadInvoices = modelLoadInvoices(this)
        modelLoadInvoices.loadDataInvoices()
    }

    override fun loadthanhcong(invoicesShiper: InvoicesShiper) {
        super.loadthanhcong(invoicesShiper)
        viewLoadInvoince.loadThanhCong(invoicesShiper)
    }

    override fun loadthatbai() {
        super.loadthatbai()
        viewLoadInvoince.loadthatbai()
    }
}