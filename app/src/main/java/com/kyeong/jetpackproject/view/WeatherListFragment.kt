package com.kyeong.jetpackproject.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kyeong.jetpackproject.databinding.FragmentWeatherListBinding
import com.kyeong.jetpackproject.network.model.Weather
import com.kyeong.jetpackproject.view.adapter.WeatherRVAdapter


class WeatherListFragment : Fragment() {
    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var binding : FragmentWeatherListBinding
    private val weatherInfoList = ArrayList<Weather.Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list : List<Weather.Items>
        viewModel.getWeatherInfo("JSON",14,1,
            20231117,1100,"60","127")
        viewModel.weatherResponse.observe(requireActivity()){
            val weatherRVAdapter = WeatherRVAdapter(requireContext(), it )
            binding.weatherListRV.adapter = weatherRVAdapter
            binding.weatherListRV.layoutManager = LinearLayoutManager(requireContext())

            for(i in it){

                Log.d("result", "$i")
            }
        }


    }
}