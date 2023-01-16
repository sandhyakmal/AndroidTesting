package com.example.recyclerview

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.adapter.BCAFAdapter
import com.example.recyclerview.adapter.BiodataAdapter
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_list_biodata.*
import kotlinx.android.synthetic.main.activity_main.*

class ListBiodata : AppCompatActivity() {

    var listBiodatas = arrayListOf<Biodata>()

    lateinit var biodataAdapter: BiodataAdapter

    val alauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        val data = it.data?.getParcelableExtra<Biodata>("biodata")
        Toast.makeText(baseContext, data.toString(), Toast.LENGTH_LONG).show()

        if (data != null) {
            listBiodatas.add(data)
            biodataAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_biodata)

        biodataAdapter = BiodataAdapter(listBiodatas)

        listBiodata.apply {
            layoutManager = LinearLayoutManager(this@ListBiodata)
            adapter = biodataAdapter
        }

        btnTambah.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, AddBiodata::class.java)
            alauncher.launch(intent)
        })
    }

    @Parcelize
    data class Biodata(val nama: String, val umur: String, val alamat: String, val foto: String): Parcelable
}