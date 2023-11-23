package com.kyeong.jetpackproject.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.kyeong.jetpackproject.databinding.FragmentSaveWeatherInfoBinding
import com.kyeong.jetpackproject.view.adapter.SaveWeatherRVAdapter

class SaveWeatherInfoFragment : Fragment() {
    private val viewModel by viewModels<WeatherViewModel>()
    private lateinit var binding: FragmentSaveWeatherInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveWeatherInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animation: LottieAnimationView = binding.animationView

        viewModel.getAllWeatherInfoData()
        viewModel.selectedList.observe(viewLifecycleOwner) {
            animation.visibility =
                if (it.isNullOrEmpty()) View.VISIBLE else View.GONE


            val saveWeatherRVAdapter = SaveWeatherRVAdapter(requireContext(), it)
            binding.saveWeatherListRV.adapter = saveWeatherRVAdapter
            binding.saveWeatherListRV.layoutManager = LinearLayoutManager(requireContext())

            saveWeatherRVAdapter.itemClick = object : SaveWeatherRVAdapter.ItemClick{
                override fun onClick(view: View, position: Int) {
                    AlertDialog.Builder(context)
                        .setCancelable(false)
                        .setMessage("날씨 정보를 삭제하시겠습니까?")
                        .setNegativeButton(android.R.string.cancel) { _, _ -> }
                        .setPositiveButton(android.R.string.ok) { _, _ ->
                            viewModel.removeWeatherInfo(it[position])
                        }
                        .create()
                        .show()
                }
            }



        }
    }
}