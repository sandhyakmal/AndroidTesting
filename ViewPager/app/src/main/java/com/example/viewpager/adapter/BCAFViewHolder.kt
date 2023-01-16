package com.example.recyclerview.adapter

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewpager.interfaces.BCAFListListener
import com.example.viewpager.data.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.itemuser.view.*

//View ItemUser
class BCAFViewHolder(view: View,parent: BCAFListListener): RecyclerView.ViewHolder(view) {

    val view = view
    val parent = parent

    fun setData(context: Context, data: User, position: Int){
        view.txtNamaUser.setText(data.nama)
        view.txtAlamatUser.setText(data.alamat)
        view.txtPhoneUser.setText(data.phone)

        Glide.with(context).load(data.gambar).into(view.imageView)

        view.btnDelete.setOnClickListener(View.OnClickListener {
            val db = Firebase.firestore
            db.collection("User").document(data.id)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Berhasil Hapus Data : ${data.nama}", Toast.LENGTH_LONG).show()
                    parent.onRefresh()
                }
                .addOnFailureListener{
                    Toast.makeText(context, "Gagal Hapus Data", Toast.LENGTH_LONG).show()
                }
        })
    }

}

