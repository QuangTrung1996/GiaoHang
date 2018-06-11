package com.kltn.congphuc.giaohang.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class retrofirClient {
    companion object {
        private var retrofit:Retrofit? = null
        public fun getClient(baseURL:String):Retrofit{
          var builder:OkHttpClient = OkHttpClient.Builder()
                  .readTimeout(20000,TimeUnit.MILLISECONDS)
                  .writeTimeout(20000,TimeUnit.MILLISECONDS)
                  .connectTimeout(20000,TimeUnit.MILLISECONDS)
                  .retryOnConnectionFailure(true)
                  .build()

                var gson:Gson = GsonBuilder().setLenient().create()
             retrofit =Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(builder)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                     .build()
            return retrofit!!
        }
    }
}