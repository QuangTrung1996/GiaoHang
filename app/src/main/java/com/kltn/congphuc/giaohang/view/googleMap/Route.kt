package com.kltn.congphuc.giaohang.view.googleMap

import com.google.android.gms.maps.model.LatLng
import com.kltn.congphuc.giaohang.view.googleMap.Distance
import com.kltn.congphuc.giaohang.view.googleMap.Duration

class Route{
    var distance: Distance? = null
    var duration: Duration? = null
    var endAddress: String? = null
    var endLocation: LatLng? = null
    var startAddress: String? = null
    var startLocation: LatLng? = null

    var points: List<LatLng>? = null
}