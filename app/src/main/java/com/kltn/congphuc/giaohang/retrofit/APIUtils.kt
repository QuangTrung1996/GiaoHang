package com.kltn.congphuc.giaohang.retrofit

class APIUtils {
    companion object {
        val baseURL:String ="https://gentle-dawn-11577.herokuapp.com/"
        val UrlPostRequet ="http://gentle-dawn-11577.herokuapp.com/"
        private var baseURLFCM = "https://fcm.googleapis.com/"
        fun getdata():dataClient{
            return retrofirClient.getClient(baseURL).create(dataClient::class.java)

        }
        fun postRequet():dataClient{
            return retrofirClient.getClient(UrlPostRequet).create(dataClient::class.java)
        }
        fun postRequestCancel():dataClient{
            return retrofirClient.getClient(UrlPostRequet).create(dataClient::class.java)

        }
        fun postRequestConfirmDelivey():dataClient{
            return retrofirClient.getClient(UrlPostRequet).create(dataClient::class.java)

        }
        fun postUpdateUser():dataClient{
            return retrofirClient.getClient(UrlPostRequet).create(dataClient::class.java)

        }
        fun sendNotiFiCaTiOn():dataClient{
            return retrofirClient.getClient(baseURLFCM).create(dataClient::class.java)

        }
    }
}