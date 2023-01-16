package com.bcaf.bcafretrofit.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bcaf.bcafretrofit.R
import com.bcaf.bcafretrofit.model.PostDummyData
import kotlinx.android.synthetic.main.item_dummy.view.*

class DummyListAdapter : RecyclerView.Adapter<DummyListAdapter.ViewHolder>() {

    var data: List<PostDummyData> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dummy, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: PostDummyData) = with(itemView) {

            itemView.txtNama.setText(item.text)
            itemView.txtUrlMovie.setText(item.image)
            itemView.txtLike.setText(""+item.likes)
            itemView.txtTag.setText(""+item.tags)
        }
    }
}