package com.example.helloworld

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*
import org.mariuszgromada.math.mxparser.Expression

class Calculator : AppCompatActivity() {

    var lastComma = false
    var lastDigit = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
    }

    fun getDigitPress(view: View){
        txtHasil.append((view as Button).text)
        lastDigit = true
        lastComma = false
    }

    fun onClear(view: View){
        txtHasil.setText("")
    }

    fun getOperatorPress(view: View){
        if (lastDigit && !lastComma){
            txtHasil.append((view as Button).text)
            lastDigit = false
            lastComma = false
        }
    }

    fun onResult(view: View){
        val e = Expression(txtHasil.text.toString())
         txtHasil.setText(e.calculate().toString())
    }

    fun onCommaPress(view: View){
        if (lastDigit && !lastComma){
            txtHasil.append((view as Button).text)
            lastDigit = false
            lastComma = true
        }
    }
}


