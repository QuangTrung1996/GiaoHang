package com.kltn.congphuc.giaohang.view.Main

import android.content.res.Resources
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kltn.congphuc.giaohang.R
import kotlinx.android.synthetic.main.fragment_main.*
import android.support.v7.app.AppCompatActivity
import android.util.Log


/**
 * Created by congp on 3/17/2018.
 */
class fragmentMain: Fragment() {
    var idvoice="id"
    private var tab: TabLayout? =null
    private var pagerAdapter: fragmentMainPagerAdapter?=null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_main, container, false);
        val bundle = arguments
        if (bundle !=null)
        {
            idvoice = bundle!!.getString("idvoice")
            Log.d("fragmentment",bundle!!.getString("idvoice"))
        }
        tab = view.findViewById(R.id.tablayout)
        val pager = view.findViewById<ViewPager>(R.id.viewpagerHome)
        (activity as AppCompatActivity).supportActionBar!!.title = "đơn hàng"
        pagerAdapter = fragmentMainPagerAdapter(this.activity.supportFragmentManager, idvoice)

        pagerAdapter!!.addfragment(fragmentHome(),"tab1")
        pagerAdapter!!.addfragment(fragmentTransportFee(),"tab2")
        pagerAdapter!!.addfragment(fragmentGoodsReceived(),"tab3")
        pagerAdapter!!.addfragment(fragmentMap(),"tab4")
        pager.adapter = pagerAdapter
        tab!!.setupWithViewPager(pager)
        tab!!.getTabAt(0)!!.setIcon(R.drawable.homered)
        tab!!.getTabAt(1)!!.setIcon(R.drawable.dollarblack)
        tab!!.getTabAt(2)!!.setIcon(R.drawable.ship)
        val icon = tab!!.getTabAt(3)!!.setIcon(R.drawable.mapblack)
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                when(position){
                    0->{
                        tab!!.getTabAt(0)!!.setIcon(R.drawable.homered)
                        tab!!.getTabAt(1)!!.setIcon(R.drawable.dollarblack)
                        tab!!.getTabAt(2)!!.setIcon(R.drawable.ship)
                        val icon = tab!!.getTabAt(3)!!.setIcon(R.drawable.mapblack)
                        (activity as AppCompatActivity).supportActionBar!!.title = "đơn hàng"
                    }
                    1->{
                        tab!!.getTabAt(0)!!.setIcon(R.drawable.homeblack)
                        tab!!.getTabAt(1)!!.setIcon(R.drawable.dollarred)
                        tab!!.getTabAt(2)!!.setIcon(R.drawable.ship)
                        val icon = tab!!.getTabAt(3)!!.setIcon(R.drawable.mapblack)
                        (activity as AppCompatActivity).supportActionBar!!.title = "tiền thu hộ"
                    }
                    2->{
                        tab!!.getTabAt(0)!!.setIcon(R.drawable.homeblack)
                        tab!!.getTabAt(1)!!.setIcon(R.drawable.dollarblack)
                        tab!!.getTabAt(2)!!.setIcon(R.drawable.shipred)
                        val icon = tab!!.getTabAt(3)!!.setIcon(R.drawable.mapblack)
                        (activity as AppCompatActivity).supportActionBar!!.title = "hàng đã nhận"

                    }
                    3 ->{
                        tab!!.getTabAt(0)!!.setIcon(R.drawable.homeblack)
                        tab!!.getTabAt(1)!!.setIcon(R.drawable.dollarblack)
                        tab!!.getTabAt(2)!!.setIcon(R.drawable.ship)
                        val icon = tab!!.getTabAt(3)!!.setIcon(R.drawable.mapred)
                        (activity as AppCompatActivity).supportActionBar!!.title = "bản đồ"
                    }
                }

            }

        })

        return view
    }


}