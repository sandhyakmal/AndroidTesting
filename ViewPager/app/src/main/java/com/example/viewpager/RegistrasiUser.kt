package com.example.viewpager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registrasi_user.*

class RegistrasiUser : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi_user)

        FirebaseApp.initializeApp(this)
        firebaseAuth = FirebaseAuth.getInstance()

        btnRegister.setOnClickListener(View.OnClickListener {

            if (!txtRegEmail.text.toString().equals("") && !txtRegPassword.text.toString().equals("") && !txtRePassword.text.toString().equals("")){
                if (txtRegPassword.text.toString().equals(txtRePassword.text.toString())){
                    createUser()
                } else {
                    Toast.makeText(this, "Maaf Password harus sama",Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Data harus diisi Semua!",Toast.LENGTH_LONG).show()
            }
        })

    }

    fun createUser(){
        firebaseAuth.createUserWithEmailAndPassword(txtRegEmail.text.toString(),txtRegPassword.text.toString()).addOnCompleteListener{
                task -> if (task.isSuccessful) {
            Toast.makeText(this, "Berhasil Register!", Toast.LENGTH_LONG).show()
        }
            finish()
        }
    }
}