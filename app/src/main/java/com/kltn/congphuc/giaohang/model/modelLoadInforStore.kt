package com.kltn.congphuc.giaohang.model

import android.util.Log
import com.kltn.congphuc.giaohang.dataRetrofit.Store.thongTinCuaHang
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import com.kltn.congphuc.giaohang.retrofit.APIUtils
import com.kltn.congphuc.giaohang.retrofit.dataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class modelLoadInforStore(var userNameShipper:String,var pass:String,var respondLoadStore: respondLoadStore) {
    fun loadDataStore(){
        val api: dataClient = APIUtils.getdata()
        val query:String = "{authenticatedShipper(email:\"".plus(""+userNameShipper+"\",".plus("pass:\""+pass+"\")"))+"{store{\n" +
                "      name\n" +
                "      location{\n" +
                "        address\n" +
                "      }\n" +
                "      owner{\n" +
                "        name\n" +
                "        email\n" +
                "        phone\n" +
                "        img\n"+
                "      }\n" +
                "      shippers{\n" +
                "        email\n" +
                "        name\n" +
                "        phone\n" +
                "        img\n"+
                "      }\n" +
                "    }\n" +
                "  }" +
                "}"
        val call: Call<thongTinCuaHang> = api.getInforStore(query)
        call.enqueue(object : Callback<thongTinCuaHang> {
            override fun onResponse(call: Call<thongTinCuaHang>, response: Response<thongTinCuaHang>) {
                if (response.isSuccessful) {
                    val thongtin: thongTinCuaHang = response.body()!! as thongTinCuaHang
                    if (thongtin.data!!.authenticatedShipper!!.store!!.name !=null) {
//                        val a = user.data!!.authenticatedShipper!!.email
//                       Log.d("testname", invoice.data!!.invoices!!.get(0).customer!!.name)
                        respondLoadStore.loadStoreThanhCong(thongtin)
                    } else {
                        respondLoadStore.loadStoreThatBai()
                    }

                }
                else
                {
                    respondLoadStore.loadStoreThatBai()
                }
            }


            override fun onFailure(call: Call<thongTinCuaHang>, t: Throwable) {
                Log.d("loadinvoice",t.toString())
                respondLoadStore.loadStoreThatBai()

            }
        })
    }
}