package com.kltn.congphuc.giaohang.view.Store

import com.kltn.congphuc.giaohang.dataRetrofit.Store.thongTinCuaHang

interface viewLoadStore {
    fun loadStoreThanhCong(thongTin:thongTinCuaHang)
    fun loadStoreThatBai()
}