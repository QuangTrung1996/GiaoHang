package com.kltn.congphuc.giaohang

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.kltn.congphuc.giaohang.dataRetrofit.User
import com.kltn.congphuc.giaohang.model.sharedPreferences
import com.kltn.congphuc.giaohang.myFirebaseIdService.Common
import com.kltn.congphuc.giaohang.presenter.presenterLogin
import com.kltn.congphuc.giaohang.view.viewLogin
import com.travel.phuc.trung.tlcn.tlcn.Conect.CheckInternet
import com.travel.phuc.trung.tlcn.tlcn.Conect.CheckInternetInterface
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlin.collections.ArrayList

class LogIn : AppCompatActivity(),viewLogin,CheckInternetInterface{

    var internet=true
    private var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportActionBar!!.hide()
        val scale:Animation = AnimationUtils.loadAnimation(this,R.anim.animation_scale)
        showpass.setOnClickListener({
            passwword.transformationMethod = HideReturnsTransformationMethod.getInstance();
            passwword.inputType = InputType.TYPE_CLASS_TEXT
            object : CountDownTimer(800, 200) {
                override fun onTick(p0: Long) {
                }

                override fun onFinish() {
                    passwword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD;
                    passwword.transformationMethod = PasswordTransformationMethod.getInstance();
                }

            }.start()

        })
        val share:sharedPreferences = sharedPreferences(this)
        val arrayList:ArrayList<String> = share.docHetListemail()
        if (arrayList != null && arrayList.size >0)
        {
            val adapter = ArrayAdapter<String>(this,android.R.layout.select_dialog_item,arrayList)
            username.threshold = 1
            username.setAdapter<ArrayAdapter<String>?>(adapter)
        }
        login.setOnClickListener({
            val checkInternet =CheckInternet(this)
            checkInternet.checkConnection(this)

            if (internet==true){
                login.startAnimation()
                flag = false
                signup.startAnimation(scale)
                signup.animation.fillAfter = true
                val userName:String = username.text.toString().trim()
                val passWord:String = passwword.text.toString().trim()
                val pstLogin = presenterLogin(this)
                pstLogin.chuyendangnhap(userName,passWord)
            }
            else
            {
                customToat("không có kết nối internet")
            }
            //DownloadRawData().execute("https://gentle-dawn-11577.herokuapp.com/graphql?query={customers{_id}}")
//            val intent = Intent(this@LogIn, MainActivity::class.java)
//           this@LogIn.startActivity(intent)
        })
        signup.setOnClickListener {
            if (flag)
            {
                Toast.makeText(this,"ano",Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun kiemtrainternet(flag: Boolean) {
        internet = flag
    }
    override fun thanhCong(userInfor:User) {
        super.thanhCong(userInfor)
        FirebaseMessaging.getInstance().subscribeToTopic("news")
        val luuThongTin = sharedPreferences(this)
        luuThongTin.nghiThongTin(userInfor.data!!.authenticatedShipper!!.name!!,userInfor.data!!.authenticatedShipper!!.address!!,userInfor!!.data!!.authenticatedShipper!!.phone!!,
                userInfor!!.data!!.authenticatedShipper!!.token!!,userInfor!!.data!!.authenticatedShipper!!.id!!,
                userInfor!!.data!!.authenticatedShipper!!.img!!)
        luuThongTin.nghiThongTinlistemail(username.text.toString().trim())
        val intent = Intent(this@LogIn, MainActivity::class.java)
        this@LogIn.startActivity(intent)

        //val a:String = luuThongTin.docThongTin()
        //Log.d("d",userInfor!!.data!!.authenticatedShipper!!.name)
        login.stopAnimation()
        finish()
    }

     override fun thieudulieu(a:Int) {
        super.thieudulieu(a)
        // Log.d("aaa",a.toString())
         flag =true
         signup.animation.fillAfter = false
         login.revertAnimation {
             login.background = resources.getDrawable(R.drawable.background_login)
             login.setText("Thử lại")
             login.setTextColor(Color.RED)
         }
         login.stopAnimation()
//         login.doneLoadingAnimation(Color.GREEN,BitmapFactory.decodeResource(resources,R.drawable.ic_done_white_48dp))
         login.setText("Thử lại")
         customToat("email hoặc password không được để trống")
     }
     override fun thatBai() {
        super.thatBai()
         flag =true
         signup.animation.fillAfter = false
         login.revertAnimation {
             login.background = resources.getDrawable(R.drawable.background_login)
             login.setText("Thử lại")
             login.setTextColor(Color.RED)
         }
         customToat("email hoặc password không đúng")
         login.stopAnimation()
         login.setText("Thử lại")

    }
    fun customToat(noidung:String){
        val layoutInflater:LayoutInflater = getLayoutInflater()
        val view:View = layoutInflater.inflate(R.layout.custom_toast,null)
        val txtToat:TextView = view.findViewById(R.id.TXTtoat)
        txtToat.text = noidung
        val toat:Toast = Toast(this@LogIn)
        toat.view = view
        toat.duration = Toast.LENGTH_SHORT
        toat.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 150)
        toat.show()
    }
}
