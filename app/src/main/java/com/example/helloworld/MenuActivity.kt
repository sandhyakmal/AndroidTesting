package com.example.helloworld

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month
import java.time.MonthDay
import java.time.Period
import java.time.Year
import java.util.*

class MenuActivity : AppCompatActivity() {

    var username = ""
    var password = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val datas: Bundle? = intent.extras
        username = datas?.getString("username","").toString()
        password = datas?.getString("password","").toString()

        txtHello.text = "Selamat Datang $username"
        animateText()

        btnPilihTanggal.setOnClickListener(View.OnClickListener {
            pickDate(it)
//            var button = Button(applicationContext)
//            button.text = "Button "+ counter++
//            linear.addView(button)
        })

        btnDial.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:08222222222")
            }
                startActivity(intent)
        })
    }


    fun animateText(){
        val anim = AlphaAnimation(0.0f, 1f)
        anim.duration = 50
        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        txtHello.startAnimation(anim)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun pickDate(view: View){

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dateSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
                c.set(Calendar.YEAR,year)
                c.set(Calendar.MONTH,month)
                c.set(Calendar.DAY_OF_MONTH,day)

                val between = Period.between(LocalDate.of(year, month+1, day),LocalDate.now())
                txtEditUmur.setText("Umur anda adalah ${between.years} Tahun ${between.months} Bulan ${between.days} Hari")

                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                txtTglLahir!!.text = sdf.format(c.getTime())

            }

        }

        DatePickerDialog(this, dateSetListener,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        ).show()

    }


}
