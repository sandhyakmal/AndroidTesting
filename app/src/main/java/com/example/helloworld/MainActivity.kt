package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val defaultUsername = "Sandhy"
    val defaulPassword = "12345"
//    var counter = 0


    fun init(){

        btnLogin.setOnClickListener(View.OnClickListener {
            checkLogin(it)
//            var button = Button(applicationContext)
//            button.text = "Button "+ counter++
//            linear.addView(button)
        })

        txtForgetPassword.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "Lupa Password", Toast.LENGTH_SHORT).show()
//            finish()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun checkLogin(v: View){
        if (txtUsername.text.contentEquals(defaultUsername) && txtPassword.text.contentEquals(defaulPassword)){

            val intent = Intent(this,Portofolio::class.java)
            intent.putExtra("username",txtUsername.text.toString())
            intent.putExtra("password",txtPassword.text.toString())
            startActivity(intent)

            Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Username atau Password salah!!!", Toast.LENGTH_SHORT).show()
        }

    }

}