package com.example.recyclerview.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.ListBiodata
import com.example.recyclerview.MainActivity
import kotlinx.android.synthetic.main.biodata.view.*
import kotlinx.android.synthetic.main.itemtwo.view.*
import kotlinx.android.synthetic.main.itemuser.view.*

//View ItemUser
class BiodataViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val view = view

    fun setData(context: Context, data: ListBiodata.Biodata, position: Int) {
        view.txtNama2.setText(data.nama)
        view.txtUmur2.setText(data.umur)
        view.txtAlamat2.setText(data.alamat)
//        Glide.with(context).load(data.gambar).into(view.imgGambar)
    }
}