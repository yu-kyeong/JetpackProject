package com.kyeong.jetpackproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kyeong.jetpackproject.R
import com.kyeong.jetpackproject.databinding.ActivityMainBinding
import com.kyeong.jetpackproject.repository.NetworkRepository

class MainActivity : AppCompatActivity() {

    private val netWorkRepository = NetworkRepository()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.naviBar
        val navController = findNavController(R.id.fragmentContainerView)

//        val result = netWorkRepository.getWeatherInfo()
        bottomNavigationView.setupWithNavController(navController)

    }
}