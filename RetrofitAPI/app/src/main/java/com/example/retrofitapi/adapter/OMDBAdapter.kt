package com.example.retrofitapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapi.R
import com.example.retrofitapi.model.SearchItem

class OMDBAdapter (val movie: List<SearchItem>): RecyclerView.Adapter<OMDBViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OMDBViewHolder {

        context = parent.context

        return OMDBViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie,parent,false))

    }

    override fun onBindViewHolder(holder: OMDBViewHolder, position: Int) {
            holder.setData(context,movie.get(position),position)
    }

    override fun getItemCount(): Int {
        return movie.size

    }


}