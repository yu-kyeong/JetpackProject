package com.kyeong.jetpackproject.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.kyeong.jetpackproject.databinding.FragmentWeatherListBinding
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
        cal.add(Calendar.HOUR, -1) // api 호출 가능 시간 고려
        val baseDate = Integer.parseInt(SimpleDateFormat("yyyyMMdd").format(cal.time))
        val baseTime = Integer.parseInt(SimpleDateFormat("HH00").format(cal.time))

        viewModel.getWeatherInfo(
            "JSON", 14, 1,
            baseDate, baseTime, "60", "127"
        )

        binding.dateText.text = SimpleDateFormat("yyyy.MM.dd").format(cal.time)
        binding.timeText.text = SimpleDateFormat("HH:00").format(cal.time)

        // 단기 날씨 예보 불러올 때
        viewModel.weatherResponse.observe(requireActivity()) {

            animation.visibility =
                if (it.isNullOrEmpty()) View.VISIBLE else View.GONE

            val weatherRVAdapter = WeatherRVAdapter(requireContext(), it)
            binding.weatherListRV.adapter = weatherRVAdapter
            binding.weatherListRV.layoutManager = LinearLayoutManager(requireContext())

           /* for (i in it) {
                Log.d("result", "$i")
            }*/
        }


    }
}