package com.example.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.ListBiodata
import com.example.recyclerview.MainActivity
import com.example.recyclerview.R

class BiodataAdapter (val biodata: List<ListBiodata.Biodata>): RecyclerView.Adapter<BiodataViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BiodataViewHolder {

        context = parent.context

        return BiodataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.biodata,parent,false))

    }

    override fun onBindViewHolder(holder: BiodataViewHolder, position: Int) {
            holder.setData(context,biodata.get(position),position)
    }

    override fun getItemCount(): Int {
        return biodata.size

    }


}