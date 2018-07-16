package com.kltn.congphuc.giaohang.presenter

import com.google.gson.JsonObject
import com.kltn.congphuc.giaohang.model.modelUpdateInforUser
import com.kltn.congphuc.giaohang.model.respondUdateInforUser
import com.kltn.congphuc.giaohang.view.chnagInforUser.viewUpdateUser
import org.json.JSONObject

class presenterUpdateInforUser(var json: JsonObject,var viewUpdateUser: viewUpdateUser):respondUdateInforUser {
    override fun updateThanhCong() {
        viewUpdateUser.updateThanhCong()
    }

    override fun updateThatBai() {
        viewUpdateUser.updateThatBai()
    }

    fun update()
    {
        var modelUpdate= modelUpdateInforUser(this,json)
        modelUpdate.postRequetNo()
    }

}