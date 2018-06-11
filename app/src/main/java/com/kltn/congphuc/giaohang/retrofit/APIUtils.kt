package com.kltn.congphuc.giaohang.retrofit

class APIUtils {
    companion object {
        val baseURL:String ="https://gentle-dawn-11577.herokuapp.com/"
        val UrlPostRequet ="http://gentle-dawn-11577.herokuapp.com/"
        fun getdata():dataClient{
            return retrofirClient.getClient(baseURL).create(dataClient::class.java)

        }
        fun postRequet():dataClient{
            return retrofirClient.getClient(UrlPostRequet).create(dataClient::class.java)
        }

    }
}