package com.example.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager.R
import com.example.viewpager.interfaces.BCAFListListener
import com.example.viewpager.data.model.User

class BCAFAdapter (val user: List<User>): RecyclerView.Adapter<BCAFViewHolder>() {

    lateinit var context: Context
    lateinit var listenerBCAF: BCAFListListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BCAFViewHolder {

        context = parent.context
        return BCAFViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemuser,parent,false),listenerBCAF)
    }

    override fun onBindViewHolder(holder: BCAFViewHolder, position: Int) {
            holder.setData(context, user.get(position),position)
    }

    override fun getItemCount(): Int {
        return user.size
    }

    fun setListener(listener: BCAFListListener){
        listenerBCAF = listener
    }

}