package com.example.retrofitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapi.adapter.OMDBAdapter
import com.example.retrofitapi.model.OMDBResponse
import com.example.retrofitapi.model.SearchItem
import com.example.retrofitapi.interfaces.CallBackNetwork
import com.example.retrofitapi.service.NetworkConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var omdbAdapter: OMDBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearch.setOnClickListener(View.OnClickListener {
                searchMovie(txtSearch.text.toString())
        })

    }

    fun searchMovie(title:String){
        NetworkConfig().getServiceOMDB().searchMovie(title).enqueue(object : Callback<OMDBResponse>{
            override fun onResponse(call: Call<OMDBResponse>, response: Response<OMDBResponse>) {
                Log.d("Response OMDB API Search", response.toString())
                if (response.body()?.search != null) {
                    setupList(response.body()?.search as List<SearchItem>)
                } else {
                    Toast.makeText(this@MainActivity,"Data yang dimasukkan tidak valid",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<OMDBResponse>, t: Throwable) {
                Log.d("Failed Response", t.message.toString())
            }

        })
    }

    fun setupList(List:List<SearchItem>){


        omdbAdapter = OMDBAdapter(List)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = omdbAdapter
        }
    }
}