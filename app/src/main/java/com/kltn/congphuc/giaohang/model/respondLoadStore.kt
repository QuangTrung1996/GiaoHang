package com.kltn.congphuc.giaohang.model

import com.kltn.congphuc.giaohang.dataRetrofit.Store.thongTinCuaHang

interface respondLoadStore {
    fun loadStoreThanhCong(thongTinCuaHang: thongTinCuaHang)
    fun loadStoreThatBai()
}