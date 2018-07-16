package com.kltn.congphuc.giaohang.view.statistics

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import com.kltn.congphuc.giaohang.R

class expdStatistics(context: Context, mHeaderGroup:ArrayList<statisticsNgay>, mDataChild: HashMap<statisticsNgay,ArrayList<dataDetailStatistics>>): BaseExpandableListAdapter() {
    private var context:Context?=context;
    private var mHeaderGroup:ArrayList<statisticsNgay>?=mHeaderGroup
    private var mDataChild: HashMap<statisticsNgay,ArrayList<dataDetailStatistics>>?= mDataChild;
    override fun getGroup(groupPosition: Int): Any {
        return mHeaderGroup!!.get(groupPosition)    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true;
    }

    override fun hasStableIds(): Boolean {
        return false
    }


    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        view=convertView
        if (convertView == null) {
            val inflater = this.context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.row_statistict_header, null)

        }
        val tenquan = view!!.findViewById<TextView>(R.id.ngay) as TextView
        tenquan.text=mHeaderGroup!!.get(groupPosition).ngay.toString()
        tenquan.setOnClickListener {
            if (isExpanded)
                (parent as ExpandableListView).collapseGroup(groupPosition)
            else
                (parent as ExpandableListView).expandGroup(groupPosition, true)
        }
        return view;
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return mDataChild!!.get(mHeaderGroup!!.get(groupPosition))!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return mDataChild!!.get(mHeaderGroup!!.get(groupPosition))!!.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong();
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view:View?;
        view=convertView
        if (view==null){
            val inflater = LayoutInflater.from(context)
            view = inflater!!.inflate(R.layout.row_statistics_child, parent, false)
        }
        val id = view!!.findViewById<TextView>(R.id.id_statistics) as TextView
        val sotien = view!!.findViewById<TextView>(R.id.sotiennhanstatistics) as TextView
        val ngay = view!!.findViewById<TextView>(R.id.phivanchuyenstatistics) as TextView

        id.text=(getChild(groupPosition, childPosition) as dataDetailStatistics).id
        sotien.text=(getChild(groupPosition, childPosition) as dataDetailStatistics).tien
        ngay.text=(getChild(groupPosition, childPosition) as dataDetailStatistics).ngay

        return view     }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return mHeaderGroup!!.size
    }

}