package com.kltn.congphuc.giaohang

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessaging
import com.kltn.congphuc.giaohang.view.Main.fragmentMain
import com.kltn.congphuc.giaohang.model.sharedPreferences
import com.kltn.congphuc.giaohang.view.Store.fragmentInforStore
import com.kltn.congphuc.giaohang.view.chnagInforUser.changeInforUser
import com.kltn.congphuc.giaohang.view.statistics.fragmentStatistics
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var anh    : CircleImageView
    private lateinit var tenDN  : TextView
    private lateinit var email  : TextView
    private lateinit var  SDT : TextView
    private lateinit var ADD:CircleImageView
    var idvoice = "wellcom"
    private var RequestCode = 1000
    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var navigationView: NavigationView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseMessaging.getInstance().subscribeToTopic("myshiper")
        if (intent.extras!=null) {
            idvoice = intent.getStringExtra("idvoice")
            Log.d("phuc", idvoice)
        }
        actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,drawer,R.string.open,R.string.close)
        drawer!!.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        navigationView = findViewById(R.id.navigation)
        initNavigationDrawer()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val fragmentMain = fragmentMain()
        val bundle = Bundle()
        bundle.putString("idvoice",idvoice)
        fragmentMain.arguments = bundle
        transaction.replace(R.id.content,fragmentMain).commit()
        navigationView!!.menu.findItem(R.id.trangchu).isChecked = true

//        Common.cutenToken = FirebaseInstanceId.getInstance().getToken()!!
//        Log.d("MainActivity",Common.cutenToken)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode ==RequestCode && resultCode == Activity.RESULT_OK && data !=null)
//        {
//        Toast.makeText(this,"main",Toast.LENGTH_SHORT).show()
//
//        }
    }
    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        }else
            super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            val sharedPreferences = sharedPreferences(this)
            val inforUser = sharedPreferences.docThongTin()
            tenDN = this.findViewById(R.id.userName_header)
            tenDN.text = inforUser.get(0).toString()
             SDT = this.findViewById<TextView>(R.id.SDT_header)
            SDT.text = inforUser.get(1).toString()
            ADD = this.findViewById(R.id.profile_image)
            Glide.with(this).load(inforUser.get(2))
                    .centerCrop()
                    .into(ADD)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("CommitTransaction")
    private fun initNavigationDrawer() {

        navigationView!!.setNavigationItemSelectedListener { menuItem ->
            navigationView!!.menu.findItem(R.id.thaydoithongtin).isChecked = false
            navigationView!!.menu.findItem(R.id.dangxuat).isChecked = false
            navigationView!!.menu.findItem(R.id.thongkegiaohang).isChecked = false
            navigationView!!.menu.findItem(R.id.trangchu).isChecked = false
            navigationView!!.menu.findItem(R.id.thongTinCuaHang).isChecked = false

            val id = menuItem.itemId
            menuItem.isChecked = true
            when (id) {
                R.id.trangchu -> {
                    val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    val fragmentMain = fragmentMain()
                    val bundle = Bundle()
                    bundle.putString("idvoice",idvoice)
                    fragmentMain.arguments = bundle
                    transaction.replace(R.id.content,fragmentMain).commit()
                    navigationView!!.menu.findItem(R.id.trangchu).isChecked = true
                    navigationView!!.setCheckedItem(id)
                    drawer!!.closeDrawer(GravityCompat.START)

                }
                R.id.dangxuat -> {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("news")
                    val sharedPreferences = sharedPreferences(this)
                    sharedPreferences.xoaTaiKhoan()
                    val intent =Intent(this@MainActivity,LogIn::class.java)
                    this@MainActivity.startActivity(intent)
                    finish()
                }
                R.id.thaydoithongtin ->{
                    val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.content, changeInforUser()).commit()
                    navigationView!!.setCheckedItem(id)
                    drawer!!.closeDrawer(GravityCompat.START)
                }
                R.id.thongkegiaohang ->{
                     val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.content,fragmentStatistics()).commit()
                    navigationView!!.setCheckedItem(id)
                    drawer!!.closeDrawer(GravityCompat.START)
                }
                R.id.thongTinCuaHang ->{
                    val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.content,fragmentInforStore()).commit()
                    navigationView!!.setCheckedItem(id)
                    drawer!!.closeDrawer(GravityCompat.START)
                }

            }
            true
        }
        actionBarDrawerToggle!!.syncState()
    }
}
