package com.kltn.congphuc.giaohang.view.chnagInforUser

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.gson.JsonObject
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.model.sharedPreferences
import com.kltn.congphuc.giaohang.presenter.presenterUpdateInforUser
import kotlinx.android.synthetic.main.activity_change_image_shipper.*
import kotlinx.android.synthetic.main.activity_change_infor_item.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

class changeImageShipper : AppCompatActivity(),viewUpdateUser {
    override fun updateThanhCong() {
        val luuThongTin = sharedPreferences(this)
        luuThongTin.ghiImg( url!!)
        Log.d("aaa",url)
        val intent: Intent = Intent()
        intent.putExtra("code",1)
        setResult(Activity.RESULT_OK,intent)
        this.finish()
    }

    override fun updateThatBai() {
        lineImage.visibility = FrameLayout.INVISIBLE
        Toast.makeText(this,"That bai",Toast.LENGTH_SHORT).show()
    }

    private val PICK_IMAGE_REQUEST = 4
   private val storageRef  : StorageReference = FirebaseStorage.getInstance().reference
    private var thongtin:ArrayList<String> = ArrayList()
    private var json: JsonObject = JsonObject()
    var url :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_image_shipper)
        supportActionBar!!.hide()
        val userInfor: sharedPreferences = sharedPreferences(this)
        thongtin =userInfor.docThongTin()
        val intent =  Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        huyImageUser.setOnClickListener {
            this.finish()
        }
        thaydoiImageUser.setOnClickListener {

            startActivityForResult(Intent.createChooser(intent,"select picture"),PICK_IMAGE_REQUEST)

        }
        capnhatImageUser.setOnClickListener {
            lineImage.visibility = FrameLayout.INVISIBLE
            json.addProperty("_id",thongtin[3])
            val nameImage = "ugao"
            val ref: StorageReference = storageRef.child(thongtin[5] + "/" + nameImage + ".png")
            imageChange!!.setDrawingCacheEnabled(true)
            imageChange!!.buildDrawingCache()
            val bitmap = imageChange!!.getDrawingCache()
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val uploadTask = ref.putBytes(data)
            uploadTask.addOnFailureListener(OnFailureListener {
                lineImage.visibility = FrameLayout.VISIBLE
                Toast.makeText(this, "hãy thử lại", Toast.LENGTH_SHORT).show()
            }).addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                ref.downloadUrl.addOnCompleteListener {taskSnapshot ->
                    taskSnapshot.result
                    Log.d("url", taskSnapshot.result.toString())
                    json.addProperty("img", taskSnapshot.result.toString())
                    url = taskSnapshot.result.toString()
                    val presenter = presenterUpdateInforUser(json, this)
                    presenter.update()
                }

            })
        }
        startActivityForResult(Intent.createChooser(intent,"select picture"),PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data!!.data != null) {
                val bm = BitmapFactory.decodeStream(this.contentResolver.openInputStream(data.data))
            imageChange!!.setImageBitmap(bm)
//            try {
//                val  imageUri = data.getData();
//                val  imageStream = this.getContentResolver().openInputStream(imageUri);
//                val  selectedImage = BitmapFactory.decodeStream(imageStream);
//                imageChange!!.setImageBitmap(selectedImage);
//            } catch (e: FileNotFoundException) {
//                e.printStackTrace();
//                Toast.makeText(this, "thử lại", Toast.LENGTH_LONG).show();
//            }



        } else {
            Toast.makeText(this, "vui lòng thử lại", Toast.LENGTH_SHORT).show()
        }
    }
}
