package com.kyeong.jetpackproject.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.kyeong.jetpackproject.R
import com.kyeong.jetpackproject.databinding.FragmentWeatherListBinding
import com.kyeong.jetpackproject.view.adapter.SpinnerOptionAdapter
import com.kyeong.jetpackproject.view.adapter.WeatherRVAdapter
import java.text.SimpleDateFormat
import java.util.Calendar


class WeatherListFragment : Fragment() {
    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var binding: FragmentWeatherListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation: LottieAnimationView = binding.animationView

        val cal = Calendar.getInstance()
//        cal.add(Calendar.HOUR, -1) // api 호출 가능 시간 고려
        val baseDate = Integer.parseInt(SimpleDateFormat("yyyyMMdd").format(cal.time))
//        val baseTime = Integer.parseInt(SimpleDateFormat("HH00").format(cal.time)) // 현재 시간 고정

        binding.dateText.text = SimpleDateFormat("yyyy.MM.dd").format(cal.time)
        val list = listOf("11:00","14:00","17:00","21:00","23:00")

        binding.spinner.adapter = SpinnerOptionAdapter(requireContext(), R.layout.item_spinner_option, list)
        binding.spinner.onItemSelectedListener =  object: AdapterView.OnItemSelectedListener  {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val value = binding.spinner.getItemAtPosition(p2).toString().replace(":","")
                viewModel.getWeatherInfo(
                    "JSON", 14, 1,
                    baseDate, Integer.parseInt(value), "96", "120"
                )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        // 일기 예보 불러올 때
        viewModel.weatherResponse.observe(viewLifecycleOwner) {
            animation.run {
                if (it.isNullOrEmpty()) {
                    visibility = View.VISIBLE
                    playAnimation()
                } else {
                    visibility = View.GONE
                }
            }

            for (i in it) {
                Log.d("selectedList", "$i")
            }
            val weatherRVAdapter = WeatherRVAdapter(requireContext(), it)
            binding.weatherListRV.adapter = weatherRVAdapter
            binding.weatherListRV.layoutManager = LinearLayoutManager(requireContext())

            weatherRVAdapter.itemClick = object : WeatherRVAdapter.ItemClick {
                override fun onClick(view: View, position: Int) {
                    AlertDialog.Builder(context)
                        .setCancelable(false)
                        .setMessage("날씨 정보를 찜하시겠습니까?")
                        .setNegativeButton(android.R.string.cancel) { _, _ -> }
                        .setPositiveButton(android.R.string.ok) { _, _ ->
                            // db update
                            viewModel.saveSelectedWeatherList(it[position])
                        }
                        .create()
                        .show()
                }
            }

        }
    }

}