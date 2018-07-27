package com.kltn.congphuc.giaohang.view.Main
import android.content.Context
import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kltn.congphuc.giaohang.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.annotations.Nullable
import android.os.CountDownTimer
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.InvoiceID
import com.kltn.congphuc.giaohang.model.sharedPreferences
import com.kltn.congphuc.giaohang.presenter.presenterLoadVoiceID
import com.kltn.congphuc.giaohang.presenter.presenterSendRequetNo
import android.location.LocationManager
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import android.support.v7.app.AlertDialog
import com.kltn.congphuc.giaohang.DetailBill
import com.kltn.congphuc.giaohang.dataRetrofit.datainvoiceSerializable
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.Invoice
import com.kltn.congphuc.giaohang.model.getLatLng
import com.kltn.congphuc.giaohang.view.*
import com.kltn.congphuc.giaohang.checkInternet.CheckInternet
import com.kltn.congphuc.giaohang.checkInternet.CheckInternetInterface


/**
 * Created by congp on 3/17/2018.
 */
class fragmentHome : Fragment(),viewLoadVoice,viewRespondNo,getLatLogn, CheckInternetInterface {
    override fun kiemtrainternet(flag: Boolean) {
        if (flag) {
            val prstload: presenterLoadVoiceID = presenterLoadVoiceID(this, idvoice)
            prstload.loadLoadVoiceID()
            chuadonhang!!.visibility = TextView.INVISIBLE
            loaprocess!!.visibility = ProgressBar.VISIBLE
        }
        else
        {
            Toast.makeText(this.context,"kiểm tra Internet của bạn",Toast.LENGTH_LONG).show()
        }
    }

    override fun LatLong(lat: Double, long: Double) {
        latti = lat
        longti = long
        if (latti== null && longti ==null) {
            latti = 39.3820793
            longti = -83.3920189
        }
    }

    override fun loadThanhCong(invoiceID: InvoiceID) {
        loaprocess!!.visibility = ProgressBar.INVISIBLE
        invoicesdata=invoiceID
        docdata(invoicesdata)
    }
    override fun loadThatBai() {
        customToat("Thủ lại")
    }

    override fun senThanhCong() {
        //textviewanimation.visibility = TextView.VISIBLE
//        val trans: Animation = AnimationUtils.loadAnimation(this.context,R.anim.translate_textview)
//        textviewanimation.startAnimation(trans)
//
//            object : CountDownTimer(2000, 200) {
//                override fun onTick(p0: Long) {
//                }
//
//                override fun onFinish() {
//                    textviewanimation.visibility = TextView.INVISIBLE
//                    tongKG.setText(tongKG.text.toString().plus(textviewanimation.text.toString()))
//
//                }
//        }.start()
        chuadonhang!!.visibility = TextView.VISIBLE
        donHangHome!!.visibility = LinearLayout.INVISIBLE
        customToat("đơn hàng đã được nhận")
//        val sharedPreferences = sharedPreferences(this.context)
//        val inforUser = sharedPreferences.docThongTin()
//        val id = inforUser.get(3)
//        val idinvoice ="5ac89717fbca612954896528"
}

    override fun senThatBai() {
        customToat("đơn hàng không tồn tại")
    }
    private fun customToat(noidung:String){
        val layoutInflater:LayoutInflater = getLayoutInflater()
        val view:View = layoutInflater.inflate(R.layout.custom_toast,null)
        val txtToat:TextView = view.findViewById(R.id.TXTtoat)
        txtToat.text = noidung
        val toat:Toast = Toast(this.context)
        toat.view = view
        toat.duration = Toast.LENGTH_LONG
        toat.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 150)
        toat.show()
    }

//    companion object {
//        var lat:Double? = null
//        var long:Double? = null
////        @JvmStatic
////        fun newInstance(text:String):fragmentHome{
////            var f: fragmentHome? = fragmentHome()
////            var b:Bundle?= Bundle()
////            b!!.putString("frm",text)
////            f!!.arguments = b
////            val idvoice = text
////            return f!!
////
////        }
//    }

    private var soTienNhan:TextView? =null
    private var loaprocess:ProgressBar?=null
    private var invoicesdata:InvoiceID = InvoiceID()
    private var idDonHang:TextView? = null
    private var tenNguoiNhan:TextView? = null
    private var diaChiNguoiNhan:TextView? = null
    private var khoiLuong:TextView? = null
    private var diaChiNhanHang:TextView?=null
    private var latKhachHang:Double = 0.0
    private var longiKhachHang:Double = 0.0
    private var xemChiTiet:Button?=null
    private var nhanDonHang:Button?=null
    private var donHangHome:LinearLayout?=null
    private var chuadonhang:TextView?=null
    private var idvoice:String = "noid"
    private var idUsser:String = ""
    private val REQUEST_LOCATION = 1
    var locationManager: LocationManager? = null
    var lattitude: String? = null
    var longitude:String? = null
    var latti:Double? = 0.0
    var longti:Double? = 0.0
    private var  diaChi:String ?= null
    var time1:Double = 0.0
    var time2:Double = 0.0
    private var latKhoHang:Double?=null
    private var longKhoHang:Double?=null
    private var thoigiangiaohome:TextView? = null
    private var sodienthoaihome:TextView? = null

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_home, container, false);
        ActivityCompat.requestPermissions(this.activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION);

        val userInfor:sharedPreferences = sharedPreferences(this.context)
        val thongtin =userInfor.docThongTin()
        diaChi = thongtin.get(4)
        anhxa(view)
        val bundle = arguments
        if (bundle!=null)
        {
            idvoice = bundle.getString("idvoice")
        }
        else
        {
            Log.d("fragmenthomenay","kỳ quá")
        }
        val inForUsser = sharedPreferences(this.context)
        val a = inForUsser.docThongTin()
        idUsser = a.get(3)
        Log.d("idUser",idUsser)
        donHangHome!!.visibility = LinearLayout.INVISIBLE
        if (!idvoice.equals("noid")&&!idvoice.equals("wellcom")) {
            val a = CheckInternet(this)
            a.checkConnection(this.context)

        }
        else
        {
            donHangHome!!.visibility = LinearLayout.INVISIBLE

        }
        nhanDonHang!!.setOnClickListener {


            val  builder =  AlertDialog.Builder(this.context);
            builder.setMessage("Bạn muốn nhận giao đơn hàng này!. ")
                    .setCancelable(false)
                    .setPositiveButton("có", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            NhanDonhang()
                        }

                    })
                    .setNegativeButton("không", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            p0!!.cancel();                    }

                    });
            val  alert: AlertDialog = builder.create();
            alert.show();

        }
        xemChiTiet!!.setOnClickListener {
            val invoice: Invoice = invoicesdata!!.data!!.invoice!!
            val data = datainvoiceSerializable(invoice)
            val intent = Intent(this.context, DetailBill::class.java)
            intent.putExtra("data",data)
            context.startActivity(intent)
        }

        return view


    }

    private fun NhanDonhang() {
        nhanDonHang!!.background = this.activity.getDrawable(R.drawable.button_onclick)
        val presenterSendRequetNo = presenterSendRequetNo(this,idvoice,idUsser,time1)
        presenterSendRequetNo.send()
    }

    private fun gettime() {
        locationManager = this.activity.getSystemService(Context.LOCATION_SERVICE)as LocationManager;
        if (!locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
        if (latti !=0.0 && longti!=0.0)
        {
            time1= setDistinace(latti!!,longti!!,latKhoHang!!,longKhoHang!!).toDouble()
            time2= setDistinace(latKhoHang!!,longKhoHang!!,latKhachHang,longiKhachHang).toDouble()
            Log.d("thoigian",time1.toString())
        }
    }

    // sét khoản cách giữa 2 điểm
    private fun setDistinace(lat1:Double,logn1:Double,lat2:Double,logn2: Double):Int {
        if (lat1 !=null&&lat2 !=null&&logn1 !=null&&logn2 !=null) {
            val locationA = Location("point A")

            locationA.latitude = lat1
            locationA.longitude = logn1

            val locationB = Location("point B")

            locationB.latitude = lat2
            locationB.longitude = logn2

            val distance: Float = locationA.distanceTo(locationB)
            val tgGiaoHnag: Int = (((distance / 1000) / 35) * 3600).toInt() * 1000
            return tgGiaoHnag
        }
        return 0

}
    // bật yêu cầu GPS
    private fun buildAlertMessageNoGps() {
        val  builder =  AlertDialog.Builder(this.context);
        builder.setMessage("Ứng dụng cần GPS của bạn")
                .setCancelable(false)
                .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        startActivity( Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }

                })
                .setNegativeButton("No", object :DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.cancel();                    }

                });
        val  alert:AlertDialog = builder.create();
        alert.show();
    }


    private fun getLocation() {
            if (ActivityCompat.checkSelfPermission(this.activity, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                    (this.activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this.activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION);

            } else {

                if (locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) != null) {
                    val location: Location =locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) ;
                    latti = location.getLatitude();
                     longti = location.getLongitude();
                    lattitude = latti.toString();
                    longitude = longti.toString();

                } else  if (locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {
                    val location1:Location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    latti = location1.getLatitude();
                    longti = location1.getLongitude();
                    lattitude = latti.toString();
                    longitude = longti.toString();

                } else  if (locationManager!!.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER) != null) {
                    val location2:Location = locationManager!!.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);
                    latti = location2.getLatitude();
                    longti = location2.getLongitude();
                    lattitude = latti.toString();
                    longitude = longti.toString();

//                    Log.d("location","Your current location is"+ "\n" + "Lattitude = " + lattitude
//                            + "\n" + "Longitude = " + longitude);

                }else{
                    val a = getLatLng(this)
                    a.execute(diaChi)

//                    Toast.makeText(this.context,"không thể xác định vị trí của bạn",Toast.LENGTH_SHORT).show();
                }
            }

    }



    private fun anhxa(view: View?) {
        diaChiNhanHang = view!!.findViewById(R.id.diachinhanhang)
        idDonHang = view.findViewById(R.id.id_donhanghome)
        tenNguoiNhan = view.findViewById(R.id.tennguoinhanhome)
        diaChiNguoiNhan = view.findViewById(R.id.diachinguoinhanhome)
        khoiLuong = view.findViewById(R.id.khoiluonghome)
        xemChiTiet = view.findViewById(R.id.xemchitiethome)
        nhanDonHang = view.findViewById(R.id.nhanDonHanghome)
        donHangHome = view.findViewById(R.id.donhanghome)
        loaprocess = view.findViewById(R.id.loadinvoice)
        chuadonhang = view.findViewById(R.id.cuacodonhang)
        soTienNhan = view.findViewById(R.id.sotiennhanhome)
        thoigiangiaohome = view.findViewById(R.id.thoigiangiaohome)
        sodienthoaihome = view.findViewById(R.id.sodienthoaihome)
    }



//    override fun ondetaill(position: Int) {
//        val invoice: Invoice = invoicesdata!!.data!!.invoice!!
//        var data = datainvoiceSerializable(invoice)
//        val intent = Intent(this.context, DetailBill::class.java)
//        intent.putExtra("data",data)
//        context.startActivity(intent)
////
//    }
    private fun docdata(invoicesShiper: InvoiceID) {
        idDonHang!!.text = invoicesShiper.data!!.invoice!!.id
        tenNguoiNhan!!.text = invoicesShiper.data!!.invoice!!.customer!!.name
        diaChiNguoiNhan!!.text = invoicesShiper.data!!.invoice!!.tasks!!.location!!.address
        latKhachHang = invoicesdata.data!!.invoice!!.store!!.location!!.lat!!
        longiKhachHang = invoicesdata.data!!.invoice!!.tasks!!.location!!.lng!!
        diaChiNhanHang!!.text = invoicesdata.data!!.invoice!!.tasks!!.location!!.address
        soTienNhan!!.text = invoicesdata.data!!.invoice!!.price.toString().plus(".000 đ")
        latKhoHang = invoicesdata.data!!.invoice!!.store!!.location!!.lat!!
        longKhoHang = invoicesdata.data!!.invoice!!.store!!.location!!.lng!!
        var kl:Float = 0f
        for (i in 0 .. invoicesShiper.data!!.invoice!!.products!!.size-1)
        {
            kl += invoicesShiper.data!!.invoice!!.products!!.get(i).quantity!!
        }
        khoiLuong!!.text = kl.toString().plus(" KG")
        donHangHome!!.visibility = LinearLayout.VISIBLE
        gettime()
        Log.d("thoiGiandukien",(time1/60000).toString()+"cộng"+ (time2/60000).toString())
        val TG = (((time1 + time2)/60000).toInt()+10)
        if (TG <=60)
        {
            thoigiangiaohome!!.text = TG.toString().plus("phút")

        }
        else
        {
            val phut = TG%60
            val gio = TG/60
            thoigiangiaohome!!.text = gio.toString().plus(" giờ: ").plus(phut.toString().plus(" phút"))

        }
        sodienthoaihome!!.text = invoicesdata.data!!.invoice!!.customer!!.phone!!

    }
}
