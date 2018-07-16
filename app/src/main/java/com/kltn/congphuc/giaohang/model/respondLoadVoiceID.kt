package com.kltn.congphuc.giaohang.model

import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.InvoiceID

interface respondLoadVoiceID {
    fun loadThanhCong(invoiceID: InvoiceID)
    fun loadThatBai()
}