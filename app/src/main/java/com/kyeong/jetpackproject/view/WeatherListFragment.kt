package com.kyeong.jetpackproject.view

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.kyeong.jetpackproject.databinding.FragmentWeatherListBinding
import com.kyeong.jetpackproject.network.model.Weather
import com.kyeong.jetpackproject.view.adapter.WeatherRVAdapter


class WeatherListFragment : Fragment() {
    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var binding : FragmentWeatherListBinding
    private val weatherInfoList = ArrayList<Weather.Item>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation : LottieAnimationView = binding.animationView

        viewModel.getWeatherInfo("JSON",14,1,
            20231117,1100,"60","127")

        viewModel.weatherResponse.observe(requireActivity()){
            animation.visibility = View.GONE
            val weatherRVAdapter = WeatherRVAdapter(requireContext(), it )
            binding.weatherListRV.adapter = weatherRVAdapter
            binding.weatherListRV.layoutManager = LinearLayoutManager(requireContext())

            /*for(i in it) {
                Log.d("result", "$i")
            }*/
        }


    }
}