package com.kltn.congphuc.giaohang.view.googleMap

interface DirectionFinderListener {
    fun onDirectionFinderStart()
    fun onDirectionFinderSuccess(route: List<Route>)
}