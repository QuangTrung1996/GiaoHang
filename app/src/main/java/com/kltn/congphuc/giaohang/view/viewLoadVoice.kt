package com.kltn.congphuc.giaohang.view

import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.InvoiceID

interface viewLoadVoice {
    fun loadThanhCong(invoiceID: InvoiceID)

    fun loadThatBai()
}