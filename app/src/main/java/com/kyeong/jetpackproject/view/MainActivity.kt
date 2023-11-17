package com.kyeong.jetpackproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.kyeong.jetpackproject.R
import com.kyeong.jetpackproject.databinding.ActivityMainBinding
import com.kyeong.jetpackproject.repository.NetworkRepository

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.weatherResponse.observe(this){
            for(i in it){
                Log.d("result", "$i")
            }
        }

        val bottomNavigationView = binding.naviBar
        val navController = findNavController(R.id.fragmentContainerView)

        bottomNavigationView.setupWithNavController(navController)

    }
}