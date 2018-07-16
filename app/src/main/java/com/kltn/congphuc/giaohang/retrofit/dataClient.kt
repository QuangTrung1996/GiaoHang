package com.kltn.congphuc.giaohang.retrofit

import com.google.gson.JsonObject
import com.kltn.congphuc.giaohang.dataRetrofit.PostRequestNo
import com.kltn.congphuc.giaohang.dataRetrofit.Store.thongTinCuaHang
import com.kltn.congphuc.giaohang.dataRetrofit.User
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Pushnotification
import com.kltn.congphuc.giaohang.dataRetrofit.dataNotificationFCM.Result
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.InvoiceID
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import com.kltn.congphuc.giaohang.dataRetrofit.respondRequestCancel
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST



interface dataClient  {

    @GET("graphql")
    fun getUserDetails(@Query("query") query: String): Call<Shippers>

//    fun getUserDetails(): Call<Shippers>

    @GET("graphql")
    fun getUserinfor(@Query("query") query: String):Call<User>
    @GET("graphql")
    fun getDataInvoice(@Query("query") query: String):Call<InvoicesShiper>

    @GET("graphql")
    fun getInforStore(@Query("query") query: String):Call<thongTinCuaHang>
    @FormUrlEncoded
    @POST("accept/invoice")
    fun postRequet(@Field ("invoiceId") invoiceId:String,
                   @Field ("shipperId") shipperId:String,
                   @Field ("estimationTime") estimationTime:Double):Call<PostRequestNo>
    @FormUrlEncoded
    @POST("receipt/invoice")
    fun postRequetFCMConfirm(@Field ("invoiceId") invoiceId:String):Call<respondRequestCancel>

    @GET("graphql")
    fun getVoiceID(@Query("query") query: String):Call<InvoiceID>

    @Headers(
            "Content-Type: application/json",
            "Authorization: key=AIzaSyBPgXg8hfnAiRBbyt1TOdKRdL6rDu1KShw"
    )
    @POST("fcm/send")
    fun senNotification(@Body Body: Pushnotification):Call<Result>
    @FormUrlEncoded
    @POST("reject/invoice")
    fun postRequestCancel(@Field ("invoiceId") invoiceId:String,
                         @Field ("storeId") storeId:String,
                         @Field ("shipperId") shipperId:String,
                         @Field ("reason") reason:String):Call<respondRequestCancel>

    @Headers("Content-Type: application/json")
    @POST("shipper/update")
    fun postUpdateUser(@Body body: JsonObject): Call<respondRequestCancel>

}