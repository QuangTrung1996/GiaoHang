package com.kltn.congphuc.giaohang.model

import android.content.Context
import android.util.Log
import com.kltn.congphuc.giaohang.dataRetrofit.User
import com.kltn.congphuc.giaohang.retrofit.APIUtils
import com.kltn.congphuc.giaohang.retrofit.Data
import com.kltn.congphuc.giaohang.retrofit.Shippers
import com.kltn.congphuc.giaohang.retrofit.dataClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class modelLogin(var responeLogin: responeLogin) {

    fun kiemTraDangNhap(tenDangNhap:String,matKhau:String){
//        DownloadRawData().execute("https://gentle-dawn-11577.herokuapp.com/graphql?query={customers{_id,name,pass}}")
        if (tenDangNhap.equals("")|| matKhau.equals(""))
        {
            responeLogin.thieudulieu(1)
        }
        else {

            val api: dataClient = APIUtils.getdata()
//                val call: Call<Shippers> = api.getUserDetails("{shippers{_id,name,pass}}")
//                call.enqueue(object : Callback<Shippers> {
//                    override fun onResponse(call: Call<Shippers>, response: Response<Shippers>) {
//                        val listshiper: Shippers = response.body()!! as Shippers
////                    Log.d("AAA", response.body().toString())
//                        val a = listshiper.data!!.shippers!!.get(1).name.toString()
//                        Log.d("AAA", a)
//
////                    if (listshiper !=null) {
////                        Log.d("AAA", "aaa")
////                    }
////                    else
////                        Log.d("AAA", "sao kỳ vậy")
//                        responeLogin.loginThanhCong()
//
//                    }
//
//                    override fun onFailure(call: Call<Shippers>, t: Throwable) {
//                        //Log.d("AAA",t.toString())
////                    Toast.makeText(getApplicationContext(), t.message, Toast.LENGTH_SHORT).show()
//                    }
//                })
            val query:String = "{authenticatedShipper(email:\"".plus(""+tenDangNhap+"\",".plus("pass:\""+matKhau+"\")"))+ "{ img,_id ,email,phone,address,name,licensePlate}}"
            val call:Call<User> = api.getUserinfor(query)
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val user: User = response.body()!! as User
                    if (user.data!!.authenticatedShipper!=null) {
//                        val a = user.data!!.authenticatedShipper!!.email
//                        Log.d("AAA", a.toString())
                        responeLogin.loginThanhCong(user)
                    }
                    else
                    {
                        Log.d("ModelLogin", "eko")
                        responeLogin.loginThatBai()
                    }

                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("AAA",t.toString())
                    responeLogin.loginThatBai()
//                    Toast.makeText(getApplicationContext(), t.message, Toast.LENGTH_SHORT).show()
                }
            })

        }


    }

//    @SuppressLint("StaticFieldLeak")
//    inner class DownloadRawData: AsyncTask<String, Void, String>() {
//        override fun doInBackground(vararg p0: String?): String {
//            val content: StringBuffer = StringBuffer()
//            val url: URL = URL(p0[0])
//            val urlConection: HttpURLConnection =url.openConnection()as HttpURLConnection
//            val inputStreamReader: InputStreamReader =InputStreamReader(urlConection.inputStream)
//            val bufferReader: BufferedReader = BufferedReader(inputStreamReader as Reader?)
//            var line:String=""
//            try {
//                do {
//                    line=bufferReader.readLine()
//                    if (line!=null){
//                        content.append(line)
//                    }
//
//                }while (line!=null)
//                bufferReader.close()
//
//            }catch (e:Exception){
//                Log.d("AAA",e.toString())
//            }
//
//            return content.toString()
//        }
//
//        @Throws(IOException::class)
//        override fun onPostExecute(data: String?) {
//            super.onPostExecute(data)
//            if (data == null)
//                return;
//           Log.d("AAA",data)
//        }
//
//    }
}