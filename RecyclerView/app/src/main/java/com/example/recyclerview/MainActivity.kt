package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.adapter.BCAFAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datas = generateData()
        val bcafAdapter = BCAFAdapter(datas)
        rView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = bcafAdapter
        }
    }
    fun generateData(): List<Product>{
        var listProduct = arrayListOf<Product>()

        listProduct.add(Product( 1 ,"https://picsum.photos/700/500?random=2" ,"Drowning and submersion due to being washed overboard from canoe or kayak, sequela"))
        listProduct.add(Product( 2 ,"https://picsum.photos/700/500?random=3" ,"Drowning overboard from canoe or kayak, sequela"))
        listProduct.add(Product( 3 ,"https://picsum.photos/700/500?random=4" ,"Drowning and  overboard from canoe or kayak, sequela"))
        listProduct.add(Product( 4 ,"https://picsum.photos/700/500?random=5" ,"Drowning and submersion overboard from canoe or kayak, sequela"))
        listProduct.add(Product( 5 ,"https://picsum.photos/700/500?random=6" ,"Drowning and submersion due to canoe or kayak, sequela"))

        return listProduct
    }

    data class Product(val id: Int, val gambar: String, val description: String)
}