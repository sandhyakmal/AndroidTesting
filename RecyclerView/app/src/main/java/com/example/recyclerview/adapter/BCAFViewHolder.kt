package com.example.recyclerview.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.MainActivity
import kotlinx.android.synthetic.main.itemtwo.view.*
import kotlinx.android.synthetic.main.itemuser.view.*

//View ItemUser
class BCAFViewHolder(view: View): RecyclerView.ViewHolder(view) {

//    val Desc    = view.txtDesc
//    val gambar  = view.imgGambar
//    val Desc2    = view.txtDescV2
//    val gambar2  = view.imgGambarV2

    val view = view

    fun setData(context: Context, data: MainActivity.Product, position: Int){
        if (position %2 ==0){
            view.txtDesc.setText(data.description)
            Glide.with(context).load(data.gambar).into(view.imgGambar)
        } else {
            view.txtDescV2.setText(data.description)
            Glide.with(context).load(data.gambar).into(view.imgGambarV2)
        }

    }

}