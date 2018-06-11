package com.kltn.congphuc.giaohang.myFirebaseIdService

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.messaging.FirebaseMessaging

class MyFirebaseIdservice: FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val refreshToken:String? = FirebaseInstanceId.getInstance().token
        Common.cutenToken = refreshToken!!
        Log.d("token", Common.cutenToken)
    }
}