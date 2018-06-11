package com.kltn.congphuc.giaohang.retrofit

import com.kltn.congphuc.giaohang.dataRetrofit.PostRequestNo
import com.kltn.congphuc.giaohang.dataRetrofit.User
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import retrofit2.Call
import retrofit2.http.*

interface dataClient  {

    @GET("graphql")
    fun getUserDetails(@Query("query") query: String): Call<Shippers>

//    fun getUserDetails(): Call<Shippers>

    @GET("graphql")
    fun getUserinfor(@Query("query") query: String):Call<User>
//    val query={authenticatedShipper(email:\"Stanley_Leannon@yahoo.com\",pass:\"kltnugao\"){ _id ,email,phone,token,address}}")
    @GET("graphql")
    fun getDataInvoice(@Query("query") query: String):Call<InvoicesShiper>

    @FormUrlEncoded
    @POST("accept/invoice")
    fun postRequet(@Field ("invoiceId") invoiceId:String,
                   @Field ("shipperId") shipperId:String,
                   @Field ("estimationTime") estimationTime:Double):Call<PostRequestNo>

}