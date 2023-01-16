package com.bcaf.bcafretrofit

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcaf.bcafretrofit.adapter.DummyListAdapter
import com.bcaf.bcafretrofit.database.DummyDatabase
import com.bcaf.bcafretrofit.model.PostDummyData
import com.bcaf.bcafretrofit.service.NetworkConfig
import kotlinx.android.synthetic.main.activity_post_data_dummy.*
import com.bcaf.bcafretrofit.model.ResponsePostDataDummy
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDataDummy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_data_dummy)

        txtTags.setOnClickListener(View.OnClickListener {
            createMultipleDialogSelect()
        })

//        loadData()

        //Post Dummy
        btnPost.setOnClickListener(View.OnClickListener { v->
            val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected == true){
                postData()

            } else {
                GlobalScope.launch {
                    var dummyData = PostDummyData(0,"60d0fe4f5311236168a109f4",txtUrl.text.toString(),txtName.text.toString(),txtLikes.text.toString().toInt(), selectionList)
                    DummyDatabase.getInstance(applicationContext).dummyDao().insertDummy(dummyData)
                    runOnUiThread({
                        loadData()
                        Toast.makeText(applicationContext,"Maaf Jaringan tidak Ada",Toast.LENGTH_LONG).show()
                    })
                }
            }
        })
    }

    fun loadData() {
        GlobalScope.launch {
            val data: List<PostDummyData> =
                DummyDatabase.getInstance(applicationContext).dummyDao().getAll()
            Log.d("Data", data.toString())
            runOnUiThread({
                var adapterx = DummyListAdapter()
                adapterx.data = data
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = adapterx
                }
                //recyclerView.adapter?.notifyDataSetChanged()
            })
        }
    }

    fun postData(){
        var dummyData = PostDummyData(0,"60d0fe4f5311236168a109f4",txtUrl.text.toString(),txtName.text.toString(),txtLikes.text.toString().toInt(), selectionList)
        NetworkConfig().getServiceDummy().postData(dummyData).enqueue(object : Callback<ResponsePostDataDummy>{
            override fun onResponse(
                call: Call<ResponsePostDataDummy>,
                response: Response<ResponsePostDataDummy>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(applicationContext,"Berhasil Input",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponsePostDataDummy>, t: Throwable) {
                Log.e("Error Post", t.printStackTrace().toString())
            }
        })
    }


    val selectionList = arrayListOf<String>()
    val listItem = arrayOf("Movies", "Actor", "Comic")
    val listChecked = booleanArrayOf(false,false,false)

    fun createMultipleDialogSelect(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose Tags")

        builder.setMultiChoiceItems(listItem,listChecked){
            dialog, which, ischecked ->
            listChecked[which] = ischecked
        }

        builder.setCancelable(false)
        builder.setNegativeButton("Cancel"){
            dialog, which ->
//            Toast.makeText(applicationContext, "Cancel",Toast.LENGTH_LONG).show()
        }
        builder.setPositiveButton("Submit"){
            dialog, which ->


            selectionList.clear()
            for ((index,value) in listChecked.withIndex()){
                if(value){
                    selectionList.add(listItem[index])
                }
            }

            txtTags.setText("")
            for (listItem in selectionList){
                txtTags.append("${listItem}, ")
            }
            txtTags.setText(txtTags.text.toString().dropLast(2))
        }
        builder.create()

        val alertDialog = builder.create()
        alertDialog.show()

    }



}