package com.bcaf.bcafretrofit.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.bcaf.bcafretrofit.MainActivity
import com.bcaf.bcafretrofit.R
import com.bcaf.bcafretrofit.fragment.DetailMovie
import com.bcaf.bcafretrofit.fragment.ListMovie
import com.bcaf.bcafretrofit.model.SearchItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var data: List<SearchItem> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SearchItem) = with(itemView) {

            itemView.txtJudulMovie.setText(item.title)
            itemView.txtTahunMovie.setText(item.year)
            Glide.with(itemView.context).load(item.poster).into(itemView.posterMovie)

            this.setOnClickListener(View.OnClickListener {

                it ->
                val ft: FragmentTransaction = (context as MainActivity).supportFragmentManager.beginTransaction()
                ft.replace(R.id.frameFragment, DetailMovie.newInstance(item.imdbID.toString(),""),"")
                    .addToBackStack("list")
                ft.commit()

            })

        }
    }
}