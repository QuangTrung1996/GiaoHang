package com.kltn.congphuc.giaohang.model

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import java.util.ArrayList

@Suppress("UNREACHABLE_CODE")
class sharedPreferences constructor(var context: Context) {
    val share = context.getSharedPreferences("userInfor",android.content.Context.MODE_PRIVATE)
    val listemail = context.getSharedPreferences("listemail",android.content.Context.MODE_PRIVATE)

    fun nghiThongTin( userNane:String,address:String,phoneNumber:String,token:String,id:String,img:String){
        val editor = share.edit()
        editor.putString("img",img)
        editor.putString("userNane",userNane)
        editor.putString("address",address)
        editor.putString("phoneNumber",phoneNumber)
        editor.putString("token",token)
        editor.putString("id",id)
        editor.apply()
    }
    fun nghiThongTinlistemail( email:String){
        val editor = listemail.edit()
        for (i in 1..3) {
            if (docThongTinlistemail(i,email) ==4)
                break
            if (docThongTinlistemail(i, email) == 1) {
                editor.putString(i.toString(), email)
                editor.apply()
                break
            } else {
                if (docThongTinlistemail(i, email) == 2) {
                    editor.putString("3", email)
                    editor.apply()
                    break
                }
            }

        }

    }
    fun docThongTinlistemail(i:Int,email:String):Int{
        val sharedPreferences =listemail
        val ten = sharedPreferences.getString(i.toString(),"null")
        if (ten == null || ten =="null")
        {
            return 1

        }
        else
        {
            if (ten == email) {
                return 4
            }
            if (i ==3) {
                return 2
            }


        }
        return 3
    }

    fun xoaTaiKhoan()
    {
        val editor = share.edit()
        editor.clear()
        editor.apply()
    }
    fun xoaHetEmail()
    {
        val editor = listemail.edit()
        editor.clear()
        editor.apply()
    }

    fun docHetListemail():ArrayList<String>{
        val listEmail:ArrayList<String> = ArrayList()
        for (i in 1..3)
        {
            val ten = listemail.getString(i.toString(),"null")
            if (ten !="null" && ten != null)
            {
                listEmail.add(ten)
            }

        }
        return listEmail
    }
    fun docThongTin():ArrayList<String>{
        var listInfor:ArrayList<String> = ArrayList()
        val sharedPreferences =share
        val id = sharedPreferences.getString("id","kltnugao")
        val ten = sharedPreferences.getString("userNane","KLTN-Ugao")
        val img = sharedPreferences.getString("img","https://scontent.fsgn2-1.fna.fbcdn.net/v/t1.0-9/29101166_2124124447810944_6472794720074252663_n.jpg?_nc_cat=0&oh=4717718034b4b3903bf64c99deb1f9e2&oe=5B8156AA")
        listInfor.add(ten)
        listInfor.add(sharedPreferences.getString("phoneNumber","KLTN-Ugao"))
        listInfor.add(img)
        listInfor.add(id)
        return listInfor
    }
}