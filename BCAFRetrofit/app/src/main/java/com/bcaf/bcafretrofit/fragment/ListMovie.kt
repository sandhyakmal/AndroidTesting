package com.bcaf.bcafretrofit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcaf.bcafretrofit.ICallBackNetwork
import com.bcaf.bcafretrofit.MainActivity
import com.bcaf.bcafretrofit.R
import com.bcaf.bcafretrofit.adapter.MovieListAdapter
import com.bcaf.bcafretrofit.model.OMDBDetailResponse
import com.bcaf.bcafretrofit.model.ResponsePostDataDummy
import com.bcaf.bcafretrofit.model.SearchItem
import kotlinx.android.synthetic.main.fragment_list_movie.*
import kotlinx.android.synthetic.main.fragment_list_movie.view.*
import kotlinx.android.synthetic.main.item_movie.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListMovie.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListMovie : Fragment() , ICallBackNetwork{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list_movie, container, false)

       view.btnSearch.setOnClickListener(View.OnClickListener {
           (activity as MainActivity).searchMovie(view.txtMovieTitle.text.toString(),this)
       })




        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListMovie.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListMovie().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    lateinit var dataMovie :List<SearchItem>
    override fun onFinish(data: List<SearchItem>) {
        dataMovie = data
        var adapterx = MovieListAdapter()
        adapterx.data = data

        recycleMovie.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterx
        }

    }

    override fun onFinishDetail(data: OMDBDetailResponse) {
        TODO("Not yet implemented")
    }


    override fun onFailed() {
       Toast.makeText(context,"Gagal mendownload data", Toast.LENGTH_LONG).show()

    }

}