package com.example.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.MainActivity
import com.example.recyclerview.R

class BCAFAdapter (val product: List<MainActivity.Product>): RecyclerView.Adapter<BCAFViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BCAFViewHolder {

        context = parent.context

        if (viewType == 0){
            return BCAFViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemuser,parent,false))
        }else {
            return BCAFViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemtwo,parent,false))
        }

    }

    override fun onBindViewHolder(holder: BCAFViewHolder, position: Int) {
            holder.setData(context,product.get(position),position)
    }

    override fun getItemCount(): Int {
        return product.size

    }

    override fun getItemViewType(position: Int): Int {
        if ( position%2 ==0){
            return 0
        } else {
            return 1
        }
    }


}