package com.kltn.congphuc.giaohang.presenter

import com.kltn.congphuc.giaohang.dataRetrofit.Store.thongTinCuaHang
import com.kltn.congphuc.giaohang.model.modelLoadInforStore
import com.kltn.congphuc.giaohang.model.respondLoadStore
import com.kltn.congphuc.giaohang.view.Store.viewLoadStore

class presenterLoadInforStore(var userNameShipper:String,var Pass:String,var viewLoadStore: viewLoadStore):respondLoadStore {
   fun loadInfor(){
       val model = modelLoadInforStore(userNameShipper,Pass,this)
       model.loadDataStore()
   }
    override fun loadStoreThanhCong(thongTinCuaHang: thongTinCuaHang) {
        viewLoadStore.loadStoreThanhCong(thongTinCuaHang)
    }

    override fun loadStoreThatBai() {
        loadStoreThatBai()
    }
}