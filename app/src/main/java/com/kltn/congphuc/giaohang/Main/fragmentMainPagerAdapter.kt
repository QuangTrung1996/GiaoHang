package com.kltn.congphuc.giaohang.Main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by congp on 3/17/2018.
 */
class fragmentMainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private  var FrmItem: ArrayList<Fragment> = ArrayList();
    private  var FrmItemtitle: ArrayList<String> = ArrayList();

    override fun getItem(position: Int): Fragment {
        var frag: Fragment? = null
        when (position) {
            0 -> frag = fragmentHome()
            1 -> frag = fragmentTransportFee()
            2 -> frag = fragmentGoodsReceived()
            3 -> frag = fragmentMap()
        }
        return frag!!
    }

    override fun getCount(): Int {
        return FrmItem.size
    }
    // them fragment vào pageradapter
    fun addfragment(frmitem:Fragment,title:String){
        FrmItemtitle.add(title)
        FrmItem.add(frmitem);
    }
//    override fun getPageTitle(position: Int): CharSequence {
//        return FrmItemtitle[position];
//    }
}