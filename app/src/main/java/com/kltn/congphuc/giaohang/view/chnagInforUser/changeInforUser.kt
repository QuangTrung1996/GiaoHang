package com.kltn.congphuc.giaohang.view.chnagInforUser

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.model.sharedPreferences
import de.hdodenhof.circleimageview.CircleImageView
import com.google.gson.JsonObject
import com.kltn.congphuc.giaohang.presenter.presenterUpdateInforUser
import kotlinx.android.synthetic.main.activity_log_in.*
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

class changeInforUser:Fragment(),viewUpdateUser {
    override fun updateThanhCong() {
        confirm!!.revertAnimation{
            confirm!!.background = this.activity.getDrawable(R.drawable.background_login)
            confirm!!.setTextColor(Color.BLACK)
            confirm!!.setText("Thành công")
        }
        val luuThongTin = sharedPreferences(this.activity)

            luuThongTin.nghiThongTin(thongtin[0], location!!.text.toString(), phone!!.text.toString(), thongtin[3],
                    thongtin[2],
                    thongtin[6],
                    email!!.text.toString(), plate!!.text.toString())
            confirm!!.background = this.activity.getDrawable(R.drawable.background_login)
            confirm!!.setText("Thành công")

    }

    override fun updateThatBai() {
        confirm!!.revertAnimation{
            confirm!!.background = this.activity.getDrawable(R.drawable.background_login)
            confirm!!.setText("Thử lại")
        }
    }

    val storageRef  : StorageReference = FirebaseStorage.getInstance().reference
    private var imageUsser:CircleImageView? = null
    private var userName:TextView?=null
    private var phone:TextView?=null
    private var email:TextView?=null
    private var plate:TextView?=null
    private var location:TextView?=null
    private var changePass:TextView? = null
    private var cameraUser:ImageView?=null
    private var  confirm: CircularProgressButton? = null
    private var thongtin:ArrayList<String> = ArrayList()
    private var editPhone:ImageView?= null
    private var editEmail:ImageView?= null
    private var editPlate:ImageView?= null
    private var editLocation:ImageView?= null
    private val RequietCode:Int=0
    private val RequietCode1:Int=10

    private var json: JsonObject = JsonObject()
    private var PICK_IMAGE_REQUEST :Int =4
    private var flag:Boolean = false
    private var huyImage:TextView?=null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_change_infor, container, false);

        val userInfor: sharedPreferences = sharedPreferences(this.context)
         thongtin =userInfor.docThongTin()
        anhxa(view)
        Log.d("vui",thongtin[3].toString())
        onclick()
        confirm!!.setOnClickListener {
            json.addProperty("_id", thongtin[3])

            if (email!!.text != thongtin[5]) {
                json.addProperty("email", email!!.text.toString())
            }
            if (phone!!.text != thongtin[1]) {
                json.addProperty("phone", phone!!.text.toString())
            }
            if (plate!!.text != thongtin[7])
                json.addProperty("licensePlate", plate!!.text.toString())
            if (location!!.text != thongtin[4]) {
                json.addProperty("address", location!!.text.toString())
            }

                if (json.size() > 1) {

                    confirm!!.startAnimation()
                    val presenter = presenterUpdateInforUser(json, this)
                    presenter.update()
                } else {
                    Toast.makeText(this.activity, "chưa có thông tin mới", Toast.LENGTH_SHORT).show()

                }

        }
        return view
    }
    private fun anhxa(view: View?) {
        huyImage = view!!.findViewById(R.id.huyImnage)
        userName = view!!.findViewById(R.id.tenShipper)
        phone = view.findViewById(R.id.phoneShipper)
        email = view.findViewById(R.id.emailShipper)
        plate = view.findViewById(R.id.plateShipper)
        location = view.findViewById(R.id.diachiShipper)
        changePass = view.findViewById(R.id.doiMatKhau)
        imageUsser = view.findViewById(R.id.imageUserShipper)
        cameraUser=view.findViewById(R.id.camereUser)
        confirm = view.findViewById(R.id.changeInfo)
        editEmail = view.findViewById(R.id.editEmail)
        editPhone = view.findViewById(R.id.editPhone)
        editLocation = view.findViewById(R.id.editLocation)
        editPlate = view.findViewById(R.id.editPlate)
        userName!!.text = thongtin[0]
        email!!.text= thongtin[5]
        plate!!.text = thongtin[7]
        phone!!.text = thongtin[1]
        location!!.text = thongtin[4]
        Glide.with(this.activity).load(thongtin[2])
                .centerCrop()
                .error(R.drawable.anhao)
                .into(imageUsser)
    }


    private fun onclick() {
        editPhone!!.setOnClickListener {
            val a = 1
            val intent:Intent = Intent(this.context,changeInforItem::class.java)
            intent.putExtra("data",phone!!.text.toString())
            intent.putExtra("code",a)
            startActivityForResult(intent,RequietCode)
        }
        editEmail!!.setOnClickListener {
            val a = 2
            val intent:Intent = Intent(this.context,changeInforItem::class.java)
            intent.putExtra("data",email!!.text.toString())
            intent.putExtra("code",a)
            startActivityForResult(intent,RequietCode)
        }
        editPlate!!.setOnClickListener {
            val a = 3
            val intent:Intent = Intent(this.context,changeInforItem::class.java)
            intent.putExtra("data",plate!!.text.toString())
            intent.putExtra("code",a)
            startActivityForResult(intent,RequietCode)
        }
        editLocation!!.setOnClickListener {
            val a = 4
            val intent:Intent = Intent(this.context,changeInforItem::class.java)
            intent.putExtra("data",location!!.text.toString())
            intent.putExtra("code",a)
            startActivityForResult(intent,RequietCode!!)
        }
        cameraUser!!.setOnClickListener {
           val intent:Intent = Intent(this.context,changeImageShipper::class.java)
            intent.putExtra("code",100)
            startActivityForResult(intent,RequietCode1!!)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RequietCode && resultCode == Activity.RESULT_OK && data != null) {
            val id = data.getIntExtra("code", 0)
            Toast.makeText(this.context, id.toString(), Toast.LENGTH_SHORT).show()

            if (id == 1) {
                phone!!.text = data.getStringExtra("data")
            } else {
                if (id == 2) {
                    email!!.text = data.getStringExtra("data")
                } else {
                    if (id == 3) {
                        plate!!.text = data.getStringExtra("data")

                    } else {
                        if (id == 4) {
                            location!!.text = data.getStringExtra("data")
                        }
                    }
                }
            }

        } else {
            if (requestCode == RequietCode1 && resultCode == Activity.RESULT_OK && data!!.data != null) {
                val userInfor: sharedPreferences = sharedPreferences(this.context)
                val thongtin1 = userInfor.docThongTin()
                Glide.with(this.activity).load(thongtin1[2])
                        .centerCrop()
                        .error(R.drawable.anhao)
                        .into(imageUsser)
            }


        }
    }


}