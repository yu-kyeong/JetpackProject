package com.kyeong.jetpackproject.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kyeong.jetpackproject.network.model.Weather
import com.kyeong.jetpackproject.repository.NetworkRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val netWorkRepository = NetworkRepository()
    private lateinit var weatherInfoList : ArrayList<Weather.Item>

    private val _weatherResponse: MutableLiveData<List<Weather.Item>> = MutableLiveData()
    val weatherResponse : LiveData<List<Weather.Item>> get() = _weatherResponse

    fun getWeatherInfo(
        dataType: String, numOfRows: Int, pageNo: Int,
        baseDate: Int, baseTime: Int, nx: String, ny: String
    ) =
        viewModelScope.launch {
            // 단기 예보 조회
            val result = netWorkRepository.getWeatherInfo(
                dataType,
                numOfRows,
                pageNo,
                baseDate,
                baseTime,
                nx,
                ny
            )

            weatherInfoList = ArrayList()

            for (weather in result.response.body.items.item) {
                try {
                    val gson = Gson()
                    val gsonToJson = gson.toJson(weather)
                    val gsonFromJson = gson.fromJson(gsonToJson, Weather.Item::class.java)

                    weatherInfoList.add(gsonFromJson)

                }catch (e : java.lang.Exception) {
                    Log.d("Error", e.toString())
                }
            }

            _weatherResponse.value = weatherInfoList
            Log.d("weather list", _weatherResponse.value.toString())

        }
    }
