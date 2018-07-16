package com.kltn.congphuc.giaohang

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.kltn.congphuc.giaohang.dataRetrofit.datainvoiceSerializable
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.Invoice
import com.kltn.congphuc.giaohang.model.getLatLng
import com.kltn.congphuc.giaohang.model.sharedPreferences
import com.kltn.congphuc.giaohang.view.getLatLogn
import com.kltn.congphuc.giaohang.view.googleMap.DirectionFinder
import com.kltn.congphuc.giaohang.view.googleMap.DirectionFinderListener
import com.kltn.congphuc.giaohang.view.googleMap.Route
import kotlinx.android.synthetic.main.activity_detail_bill.*
import java.io.UnsupportedEncodingException

class DetailBill : AppCompatActivity(), OnMapReadyCallback, DirectionFinderListener,getLatLogn {
    override fun LatLong(lat: Double, long: Double) {
        latShipper = lat
        lgnShipper = long
        if (latShipper ==null || lgnShipper == null)
        {
            latShipper=39.3820793
            lgnShipper = -83.3920189
        }
    }

    private var originMarkers:ArrayList<Marker> = ArrayList()
    private var progressDialog: ProgressDialog? = null
    private var destinationMarkers:ArrayList<Marker> = ArrayList()
    private var polylinePaths:ArrayList<Polyline> = ArrayList()
    private var flag =0
    private lateinit var mMap: GoogleMap
    private var invoice:datainvoiceSerializable?=null
    private var latShipper:Double? = null
    private var lgnShipper:Double? = null
    private var diaChi:String = ""
    private val REQUEST_LOCATION = 1
    var locationManager: LocationManager? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_bill)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("chi tiết đơn hàng")
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.mapdetail) as SupportMapFragment
        mapFragment.getMapAsync(this)
        invoice= intent.getSerializableExtra("data") as datainvoiceSerializable
        val userInfor: sharedPreferences = sharedPreferences(this)
        val thongtin =userInfor.docThongTin()
        diaChi = thongtin.get(4)
        locationManager = this.getSystemService(Context.LOCATION_SERVICE)as LocationManager;
        if (!locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getlatlong();
        }

        else{
            latShipper=39.3820793
            lgnShipper = -83.3920189
        }
        sendRequest()
        sendRequest1()
    }
    private fun buildAlertMessageNoGps() {
        val  builder =  AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        startActivity( Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }

                })
                .setNegativeButton("No", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.cancel();                    }

                });
        val  alert: AlertDialog = builder.create();
        alert.show();
    }

    private fun getlatlong() {

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                    (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION);

            } else {

                if (locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) != null) {
                    val location: Location = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    latShipper = location.getLatitude();
                    lgnShipper = location.getLongitude();
//

//                    Log.d("location","Your current location is"+ "\n" + "Lattitude = " + lattitude
//                            + "\n" + "Longitude = " + longitude);

                } else if (locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER) != null) {
                    val location1: Location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    latShipper = location1.getLatitude();
                    lgnShipper = location1.getLongitude();


//                    Log.d("location","Your current location is"+ "\n" + "Lattitude = " + lattitude
//                            + "\n" + "Longitude = " + longitude);


                } else if (locationManager!!.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER) != null) {
                    val location2: Location = locationManager!!.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                    latShipper = location2.getLatitude();
                    lgnShipper = location2.getLongitude();


//                    Log.d("location","Your current location is"+ "\n" + "Lattitude = " + lattitude
//                            + "\n" + "Longitude = " + longitude);

                } else {
                    val a = getLatLng(this)
                    a.execute(diaChi)

//                    Toast.makeText(this.context,"không thể xác định vị trí của bạn",Toast.LENGTH_SHORT).show();
                }

            }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        when (id) {
            android.R.id.home ->{
                finish()
            }

        }
        return true
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val store = LatLng(invoice!!.invvoice.store!!.location!!.lat!!, invoice!!.invvoice.store!!.location!!.lng!!)
        val customer = LatLng(invoice!!.invvoice.tasks!!.location!!.lat!!, invoice!!.invvoice.tasks!!.location!!.lng!!)
//        mMap.addMarker(MarkerOptions().position(customer).title(invoice!!.invvoice.store!!.location!!.address).icon(BitmapDescriptorFactory.fromResource(R.drawable.people)))
//        mMap.addMarker(MarkerOptions().position(store).title(invoice!!.invvoice.store!!.location!!.address).icon(BitmapDescriptorFactory.fromResource(R.drawable.storehouse)))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(store,17f))
        mMap.setMyLocationEnabled(true);

    }


    override fun onDirectionFinderStart() {
//        progressDialog = ProgressDialog.show(this, "Please wait.",
//                "Finding direction..!", true)

        if (originMarkers != null) {
            for (marker in originMarkers) {
                marker.remove()
            }
        }

        if (destinationMarkers != null) {
            for (marker in destinationMarkers) {
                marker.remove()
            }
        }

        if (polylinePaths != null) {
            for (polyline in polylinePaths) {
                polyline.remove()
            }
        }
        else{
            Toast.makeText(this,"ko tìm được đường đi",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDirectionFinderSuccess(route: List<Route>) {
//        progressDialog!!.dismiss()
        polylinePaths = ArrayList()
        originMarkers = ArrayList()
        destinationMarkers = ArrayList()
        if (flag == 0) {
            for (route in route) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 17f))
                (findViewById<View>(R.id.tvDuration) as TextView).text = route.duration!!.text
                (findViewById<View>(R.id.tvDistance) as TextView).text = route.distance!!.text

                originMarkers!!.add(mMap.addMarker(MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.storehouse))
                        .title(route.startAddress)
                        .position(route.startLocation!!)))
                destinationMarkers!!.add(mMap.addMarker(MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.people))
                        .title(route.endAddress)
                        .position(route.endLocation!!)))

                val polylineOptions = PolylineOptions().geodesic(true).color(Color.BLUE).width(6f)

                for (i in route.points!!.indices)
                    polylineOptions.add(route.points!![i])

                polylinePaths!!.add(mMap.addPolyline(polylineOptions))
            }
            flag = 1
        }
        else
        {
            for (route in route) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 17f))
                (findViewById<View>(R.id.tvDurationshipper) as TextView).text = route.duration!!.text
                (findViewById<View>(R.id.tvDistanceshipper) as TextView).text = route.distance!!.text
//
//                originMarkers!!.add(mMap.addMarker(MarkerOptions()
//                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.storehouse))
//                        .title(route.startAddress)
//                        .position(route.startLocation!!)))
//                destinationMarkers!!.add(mMap.addMarker(MarkerOptions()
//                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.people))
//                        .title(route.endAddress)
//                        .position(route.endLocation!!)))

                val polylineOptions = PolylineOptions().geodesic(true).color(Color.RED).width(7f)

                for (i in route.points!!.indices)
                    polylineOptions.add(route.points!![i])

                polylinePaths!!.add(mMap.addPolyline(polylineOptions))
            }
            loadduongdi.visibility = TextView.INVISIBLE
            flag = 0
        }

    }
    private fun sendRequest() {
        //val origin = etOrigin.text.toString()
        //val destination = etDestination.text.toString()
//        if (origin.isEmpty()) {
//            Toast.makeText(this.context, "Please enter origin address!", Toast.LENGTH_SHORT).show()
//            return
//        }
//        if (destination.isEmpty()) {
//            Toast.makeText(this.context, "Please enter destination address!", Toast.LENGTH_SHORT).show()
//            return
//        }

        try {
            val lat1 = invoice!!.invvoice!!.store!!.location!!.lat!!
            val lgn1 = invoice!!.invvoice!!.store!!.location!!.lng!!
            val lat:Double =invoice!!.invvoice!!.tasks!!.location!!.lat!!
            val lgn = invoice!!.invvoice!!.tasks!!.location!!.lng!!
            DirectionFinder(this@DetailBill,lat1,lgn1 ,lat,lgn).execute()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }
    private fun sendRequest1() {
        //val origin = etOrigin.text.toString()
        //val destination = etDestination.text.toString()
//        if (origin.isEmpty()) {
//            Toast.makeText(this.context, "Please enter origin address!", Toast.LENGTH_SHORT).show()
//            return
//        }
//        if (destination.isEmpty()) {
//            Toast.makeText(this.context, "Please enter destination address!", Toast.LENGTH_SHORT).show()
//            return
//        }

        try {
            val lat1 = invoice!!.invvoice!!.store!!.location!!.lat!!
            val lgn1 = invoice!!.invvoice!!.store!!.location!!.lng!!
            DirectionFinder(this@DetailBill,lat1,lgn1 ,latShipper!!,lgnShipper!!).execute()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }
}
