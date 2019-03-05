package com.cango.basicdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cango.basicdemo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().add(
                R.id.fragment_container,
                ProductListFragment(), ProductListFragment.TAG
            ).commit()
    }

    fun show(productId: Int) {
        supportFragmentManager.beginTransaction()
            .addToBackStack("product")
            .replace(
                R.id.fragment_container, ProductFragment.forPorduct(productId)
            )
            .commit()
    }
}
