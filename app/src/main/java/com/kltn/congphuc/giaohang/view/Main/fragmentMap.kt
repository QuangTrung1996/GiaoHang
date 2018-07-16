package com.kltn.congphuc.giaohang.view.Main

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.kltn.congphuc.giaohang.R
import org.jetbrains.annotations.Nullable
import android.widget.Toast
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE
import android.graphics.Color
import android.util.Log
import com.google.android.gms.maps.model.*
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceID.InvoiceID
import com.kltn.congphuc.giaohang.dataRetrofit.invoiceShiper.InvoicesShiper
import com.kltn.congphuc.giaohang.model.sharedPreferences
import com.kltn.congphuc.giaohang.presenter.presenterLoadInvoince
import com.kltn.congphuc.giaohang.view.googleMap.DirectionFinder
import com.kltn.congphuc.giaohang.view.googleMap.DirectionFinderListener
import com.kltn.congphuc.giaohang.view.googleMap.Route
import com.kltn.congphuc.giaohang.view.viewLoadInvoince
import com.kltn.congphuc.giaohang.view.viewLoadVoice
import java.io.UnsupportedEncodingException


/**
 * Created by congp on 3/19/2018.
 */
class fragmentMap : Fragment(),OnMapReadyCallback, GoogleMap.OnMarkerClickListener,viewLoadInvoince {


    override fun onMarkerClick(p0: Marker?): Boolean {
        if (p0!!.title == "phuc") {
        Toast.makeText(this.context,"dbyub",Toast.LENGTH_SHORT).show()
        }
        return false
    }
    private var invoicesdata:InvoicesShiper = InvoicesShiper()
    private lateinit var mMap: GoogleMap
    private lateinit var mMapView : MapView
    private lateinit var mview :View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mview = inflater!!.inflate(R.layout.fragment_main_map, container, false);
        MapsInitializer.initialize(this@fragmentMap.activity)
        val userInfor: sharedPreferences = sharedPreferences(this.context)
        val thongtin =userInfor.docThongTin()
        val userName = thongtin.get(5)
        val pass =  thongtin.get(6)
        val prstload: presenterLoadInvoince = presenterLoadInvoince(userName,pass,this)
        prstload.loadVoice()
        return mview

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMapView = mview.findViewById(R.id.map)
        if (mMapView !=null)
        {
            mMapView.onCreate(null)
            mMapView.onResume()
            mMapView.getMapAsync(this)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(text:String): fragmentMap {
            val f: fragmentMap = fragmentMap()
            val b: Bundle= Bundle()
            b.putString("frmMap",text)
            f.arguments = b
            return f

        }
    }
    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(10.8510564, 106.7698235)
        val phuc = LatLng(  10.80087,106.720359)

        //Toast.makeText(this,Lat.toString(), Toast.LENGTH_LONG).show()
//        mMap.addMarker(MarkerOptions().position(phuc).title("phuc").icon(BitmapDescriptorFactory.fromResource(R.drawable.people)))
//        mMap.addMarker(MarkerOptions().position(sydney).title("sss"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(phuc,15f))
        mMap.setMyLocationEnabled(true);
        mMap.setOnMarkerClickListener(this)
    }
    override fun loadthatbai() {
        super.loadthatbai()
//        Toast.makeText(this.context,"thử lại",Toast.LENGTH_SHORT).show()
    }

    override fun loadThanhCong(invoicesShiper: InvoicesShiper) {
        super.loadThanhCong(invoicesShiper)
        Log.d("fragmentHome","dc")
        invoicesdata=invoicesShiper
        docdata(invoicesShiper)
    }

    private fun docdata(invoicesShiper: InvoicesShiper) {
        if (invoicesdata.data !=null) {
            for (i in 0 until invoicesShiper!!.data!!.authenticatedShipper!!.unPaidInvoices!!.size) {
                val phuc = LatLng(invoicesShiper!!.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i)!!.tasks!!.location!!.lat!!,invoicesdata!!.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i)!!.tasks!!.location!!.lng!!)
                mMap.addMarker(MarkerOptions().position(phuc).title(invoicesdata!!.data!!.authenticatedShipper!!.unPaidInvoices!!.get(i)!!.tasks!!.location!!.address).icon(BitmapDescriptorFactory.fromResource(R.drawable.people)))
            }
        }
        else
        {
            Toast.makeText(this.context,"chưa có đơn hàng",Toast.LENGTH_SHORT).show()

        }
    }


}