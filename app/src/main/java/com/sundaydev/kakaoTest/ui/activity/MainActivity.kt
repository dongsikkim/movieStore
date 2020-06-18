package com.sundaydev.kakaoTest.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

val navIds = setOf(R.id.movieFragment, R.id.tvFragment, R.id.peopleFragment)

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        setSupportActionBar(binding.toolbar)
        appBarConfiguration = AppBarConfiguration(navIds)
        initNavigation()
    }

    private fun initNavigation() {
        findNavController(R.id.nav_host).run {
            setupActionBarWithNavController(this, appBarConfiguration)
            bottom_nav.setupWithNavController(this)
            addOnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.splashFragment -> {
                        toolbar.visibility = View.GONE
                        bottom_nav.visibility = View.GONE
                    }
                    R.id.detailFragment, R.id.peopleDetailFragment -> {
                        bottom_nav.visibility = View.GONE
                    }
                    else -> {
                        toolbar.visibility = View.VISIBLE
                        bottom_nav.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

}