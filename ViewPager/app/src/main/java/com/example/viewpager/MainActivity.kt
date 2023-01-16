package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.viewpager.fragment.Fragment1
import com.example.viewpager.fragment.Fragment2
import com.example.viewpager.fragment.Fragment3
import com.example.viewpager.adapter.PagerAdapter
import com.example.viewpager.interfaces.CameraInterfaces
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_2.*
import lib.android.imagepicker.ImagePicker

class MainActivity : AppCompatActivity(), ImagePicker.OnImageSelectedListener, CameraInterfaces {

    lateinit var  imagePicker: ImagePicker
    lateinit var  imagePathFile: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagePicker = ImagePicker(this, BuildConfig.APPLICATION_ID)
        imagePicker.setImageSelectedListener(this)

        var list = arrayListOf<Fragment>()
        list.add(Fragment1.newInstance("",""))
        list.add(Fragment2.newInstance("",""))
        list.add(Fragment3.newInstance("",""))
        list.add(Fragment1.newInstance("",""))
        list.add(Fragment2.newInstance("",""))
        list.add(Fragment3.newInstance("",""))
        list.add(Fragment1.newInstance("",""))

        val myPagerAdapter = PagerAdapter(this,list)

        viewPager2.adapter = myPagerAdapter

        TabLayoutMediator(tabLayout,viewPager2){tab,position ->
            tab.text = resources.getStringArray(R.array.TabMenuFragment)[position]
        }.attach()


    }

    override fun onImageSelectFailure() {
        Toast.makeText(applicationContext, "Gagal Ambil Gambar",Toast.LENGTH_LONG).show()
    }

    override fun onImageSelectSuccess(imagePath: String) {
        imagePathFile = imagePath
        Log.d("Photo File", imagePath)
        imgCamera?.let {
            Glide.with(this@MainActivity)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .load(imagePath)
                .into(it)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        imagePicker.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun openCamera() {
        imagePicker.takePhotoFromCamera()
    }

    override fun getImagePath(): String {
        return imagePathFile
    }
}