package com.example.retrofitapi.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapi.model.OMDBResponse
import com.example.retrofitapi.model.SearchItem
import kotlinx.android.synthetic.main.movie.view.*

//View ItemUser
class OMDBViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val view = view

    fun setData(context: Context, data: SearchItem, position: Int) {
        view.txtTitle.setText(data.title)
        view.txtTahun.setText(data.year)
        Glide.with(context).load(data.poster).into(view.imgPoster)
    }
}