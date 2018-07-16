package com.kltn.congphuc.giaohang.presenter

import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import com.kltn.congphuc.giaohang.model.modelLoadInvoices
import com.kltn.congphuc.giaohang.model.modelLogin
import com.kltn.congphuc.giaohang.model.responelLoadVoinces
import com.kltn.congphuc.giaohang.view.viewLoadInvoince

class presenterLoadInvoince(var tenDangNhap:String,var matKhau:String,var viewLoadInvoince: viewLoadInvoince):responelLoadVoinces {

    fun loadVoice(){
        val modelLoadInvoices = modelLoadInvoices(tenDangNhap,matKhau,this)
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