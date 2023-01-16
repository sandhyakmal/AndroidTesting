package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.viewpager.fragment.Fragment1
import com.example.viewpager.fragment.Fragment2
import com.example.viewpager.fragment.Fragment3
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_navbar.*

class NavbarActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var toogle : ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar)

        setupNavigationDrawer()
        showFirstFragment()
    }

    fun showFirstFragment(){
        val ft : FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.add(R.id.container, Fragment1.newInstance("",""))
        ft.commit()
    }

    fun setupNavigationDrawer(){
        setSupportActionBar(toolbar)
        toogle = ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.open, R.string.close)
        navigationMenu.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment : Fragment? = null

        when(item.itemId){
            R.id.weather -> {
                fragment = Fragment1.newInstance("","")
            }
            R.id.movie -> {
                fragment = Fragment2.newInstance("","")
            }
            R.id.settings -> {
                fragment = Fragment3.newInstance("","")
            }
            R.id.about -> {
                Toast.makeText(this, "Ini Halaman About", Toast.LENGTH_LONG).show()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        if ( fragment != null){
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.container,fragment)
            ft.commit()
        }

        return true

    }
}