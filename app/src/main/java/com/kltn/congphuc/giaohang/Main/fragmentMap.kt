package com.kltn.congphuc.giaohang.Main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kltn.congphuc.giaohang.R
import org.jetbrains.annotations.Nullable
import android.widget.Toast
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE



/**
 * Created by congp on 3/19/2018.
 */
class fragmentMap : Fragment(),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var mMapView : MapView
    private lateinit var mview :View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mview = inflater!!.inflate(R.layout.fragment_main_map, container, false);
        MapsInitializer.initialize(this@fragmentMap.activity)
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
        fun newInstance(text:String):fragmentMap{
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
        //Toast.makeText(this,Lat.toString(), Toast.LENGTH_LONG).show()
        mMap.addMarker(MarkerOptions().position(sydney).title("sss"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,17f))
        //mMap.setMyLocationEnabled(true);
    }

}