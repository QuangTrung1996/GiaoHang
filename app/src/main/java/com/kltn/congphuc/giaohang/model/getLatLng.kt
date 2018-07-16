package com.kltn.congphuc.giaohang.model

import android.os.AsyncTask
import android.util.Log
import com.kltn.congphuc.giaohang.view.Main.fragmentHome
import com.kltn.congphuc.giaohang.view.getLatLogn
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class getLatLng(var gatLatLogn: getLatLogn): AsyncTask<String, Void, String>() {
    //var lat:Double=9.0
    var long:Double=1.0
    companion object {
        var latget:Double=9.9
    }
    override fun doInBackground(vararg p0: String?): String {
        val DIRECTION_URL_API = "https://maps.googleapis.com/maps/api/geocode/json?address="
        val GOOGLE_API_KEY = "AIzaSyC3AXK8d8k3yM7GzZGV7JDwR36vF0kyNm4"
        val urlOrigin = URLEncoder.encode(p0[0], "utf-8")
        var URL=DIRECTION_URL_API  + urlOrigin +"&key=" + GOOGLE_API_KEY
        val content: StringBuffer = StringBuffer()
        val url: URL = URL(URL)
        val urlConection: HttpURLConnection =url.openConnection()as HttpURLConnection
        val inputStreamReader: InputStreamReader =InputStreamReader(urlConection.inputStream)
        val bufferReader: BufferedReader = BufferedReader(inputStreamReader as Reader?)
        var line:String=""
        try {
            do {
                line=bufferReader.readLine()
                if (line!=null){
                    content.append(line)
                }

            }while (line!=null)
            bufferReader.close()

        }catch (e:Exception){
            Log.d("AAA",e.toString())
        }

        return content.toString()
    }
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        var jsonoject: JSONObject = JSONObject(result)
        var latt:String= jsonoject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry")
                .getJSONObject("location").get("lat").toString()
        var lngt:String= jsonoject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry")
                .getJSONObject("location").get("lng").toString()
        //lat=latt.toDouble()
        //long=lngt.toDouble()
        //frm_DiaDiemDuLich.a=latt.toDouble()
        //Toast.makeText(this.activity,frm_DiaDiemLeHoi.a.toString(), Toast.LENGTH_LONG).show()
//        latget = latt.toDouble()
//        fragmentHome.lat = latt.toDouble()
//        fragmentHome.long = lngt.toDouble()
        gatLatLogn.LatLong(latt.toDouble(),lngt.toDouble())
    }
}