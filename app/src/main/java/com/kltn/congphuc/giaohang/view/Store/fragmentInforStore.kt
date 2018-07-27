package com.kltn.congphuc.giaohang.view.Store

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.internal.bind.TypeAdapters
import com.kltn.congphuc.giaohang.R
import com.kltn.congphuc.giaohang.dataRetrofit.Store.thongTinCuaHang
import com.kltn.congphuc.giaohang.dataRetrofit.dataRecieveRiceType
import com.kltn.congphuc.giaohang.model.sharedPreferences
import com.kltn.congphuc.giaohang.presenter.presenterLoadInforStore
import com.kltn.congphuc.giaohang.view.detailRecieveRiceTypeAdapter
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_store.*
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.*
import com.bumptech.glide.Glide


class fragmentInforStore: Fragment(),viewLoadStore {

    private lateinit var adapter: adapterShippers
    private var list:ArrayList<dataShippers> ? =ArrayList()


    override fun loadStoreThanhCong(thongTin: thongTinCuaHang) {
        docData(thongTin)
    }

    private fun docData(thongTin: thongTinCuaHang) {
        if (thongTin.data !=null) {
            tenCuaHang!!.text = thongTin.data!!.authenticatedShipper!!.store!!.name!!
            diaChi!!.text = thongTin.data!!.authenticatedShipper!!.store!!.location!!.address!!
            nameOwnerStore!!.text = thongTin.data!!.authenticatedShipper!!.store!!.owner!!.name!!
            phoneOwwner!!.text = thongTin!!.data!!.authenticatedShipper!!.store!!.owner!!.phone
            emailOwwner!!.text = thongTin.data!!.authenticatedShipper!!.store!!.owner!!.email
            for (i in 0 until thongTin.data!!.authenticatedShipper!!.store!!.shippers!!.size-1) {
                if (thongTin.data!!.authenticatedShipper!!.store!!.shippers!!.get(i).img !=null){
                        list!!.add(dataShippers(thongTin.data!!.authenticatedShipper!!.store!!.shippers!!.get(i).img!!, thongTin.data!!.authenticatedShipper!!.store!!.shippers!!.get(i)!!.name!!,
                        thongTin.data!!.authenticatedShipper!!.store!!.shippers!!.get(i).email!!, thongTin.data!!.authenticatedShipper!!.store!!.shippers!!.get(i).phone!!))
//                Toast.makeText(this.context,thongTin.data!!.authenticatedShipper!!.store!!.shippers!!.get(i).img!!,Toast.LENGTH_SHORT).show()
                }
            }
            if (thongTin!!.data!!.authenticatedShipper!!.store!!.owner!!.img !=null) {
                Glide.with(this.context).load(thongTin!!.data!!.authenticatedShipper!!.store!!.owner!!.img)
                        .placeholder(R.drawable.shipgao)
                        .error(R.drawable.shipgao)
                        .centerCrop()
                        .into(anhOwner)
            }
            adapter.notifyDataSetChanged()
            listShipper!!.adapter = adapter
            setListViewHeightBasedOnChildren(listShipper!!)
            scrollView!!.visibility = ScrollView.VISIBLE
            process!!.visibility = ProgressBar.INVISIBLE
        }
        else
        {
        }

    }


    override fun loadStoreThatBai() {

    }

    private var tenCuaHang:TextView?=null
    private var diaChi:TextView?=null
    private var  anhOwner: CircleImageView?=null
    private var nameOwnerStore:TextView?=null
    private var emailOwwner:TextView?=null
    private var phoneOwwner:TextView?=null
    private var listShipper:ListView?=null
    private var scrollView:ScrollView?=null
    private var process:ProgressBar?=null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(R.layout.fragment_store, container, false);
        anhxa(view)
        adapter = adapterShippers(this.context,list!!)
        scrollView!!.visibility = ScrollView.INVISIBLE
        process!!.visibility = ProgressBar.VISIBLE
        val userInfor: sharedPreferences = sharedPreferences(this.context)
        val thongtin =userInfor.docThongTin()
        val loadInfor = presenterLoadInforStore(thongtin[5],thongtin[6],this)
        loadInfor.loadInfor()
        return view
    }

    private fun anhxa(view: View) {
        tenCuaHang = view.findViewById(R.id.tenCuaHang)
        diaChi = view.findViewById(R.id.diaChiCuaHang)
        anhOwner = view.findViewById(R.id.imageStore)
        nameOwnerStore = view.findViewById(R.id.NameOwnerStore)
        emailOwwner = view.findViewById(R.id.emailStore)
        phoneOwwner = view.findViewById(R.id.phoneStore)
        listShipper = view.findViewById(R.id.LvShippers)
        scrollView = view.findViewById(R.id.scrollInforStore)
        process = view.findViewById(R.id.processLoadInforStore)
    }

    fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter = listView.adapter ?: return

        val desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.UNSPECIFIED)
        var totalHeight = 0
        var view: View? = null
        for (i in 0 until listAdapter.count) {
            view = listAdapter.getView(i, view, listView)
            if (i == 0)
                view!!.layoutParams = ViewGroup.LayoutParams(desiredWidth, ViewPager.LayoutParams.WRAP_CONTENT)

            view!!.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += view.measuredHeight
        }
        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
        listView.layoutParams = params
    }

}