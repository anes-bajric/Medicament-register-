package com.yourcompany.medicamentregister

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_items.view.*

class MyAdapter(val context: Context, val medicamentList: List<MyDataItem>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    class ViewHolder(itemView: View, var mListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var name: TextView
        var atc: TextView
        var color: View
        var categoryName: TextView

        init {
            name = itemView.name
            atc = itemView.atc
            color = itemView.colorBox
            categoryName = itemView.categoryName

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (mListener != null) {
                mListener.setOnClickListener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = medicamentList[position].name.toString()
        holder.atc.text = medicamentList[position].atc.toString()
        holder.color.setBackgroundColor(Color.parseColor(medicamentList[position].myAdditionalData.color))
        holder.categoryName.text = " - " + medicamentList[position].myAdditionalData.name.toString()

    }

    override fun getItemCount(): Int {
        return medicamentList.size
    }

    interface OnItemClickListener {
        fun setOnClickListener(position: Int)
    }

    fun setOnItemClickListener(mlistener: OnItemClickListener) {
        this.mListener = mlistener
    }
}