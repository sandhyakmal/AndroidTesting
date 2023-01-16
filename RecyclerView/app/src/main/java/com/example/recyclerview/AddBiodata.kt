package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_biodata.*

class AddBiodata : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_biodata)

        btnSubmit.setOnClickListener(View.OnClickListener {
            val data = ListBiodata.Biodata(txtNama.text.toString(),txtUmur.text.toString(),txtAlamat.text.toString(), "")

            val intent = Intent()
            intent.putExtra("biodata",data)
            setResult(0,intent)
            finish()

        })
    }
}