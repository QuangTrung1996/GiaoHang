package com.kltn.congphuc.giaohang.model

import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper

interface responelLoadVoinces {
    fun loadthanhcong(invoicesShiper: InvoicesShiper){
    }
    fun loadthatbai(){

    }
}