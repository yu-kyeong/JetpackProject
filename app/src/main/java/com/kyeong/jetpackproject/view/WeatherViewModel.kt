package com.kyeong.jetpackproject.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kyeong.jetpackproject.db.entity.SaveWeatherInfoEntity
import com.kyeong.jetpackproject.network.model.Weather
import com.kyeong.jetpackproject.repository.DBRepository
import com.kyeong.jetpackproject.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val netWorkRepository = NetworkRepository()
    private val dbRepository = DBRepository()
    private lateinit var weatherInfoList: ArrayList<Weather.Item>

    lateinit var selectedList : LiveData<List<SaveWeatherInfoEntity>>

    private val _weatherResponse: MutableLiveData<List<Weather.Item>> = MutableLiveData()
    val weatherResponse: LiveData<List<Weather.Item>> get() = _weatherResponse

    /**
     * 단기 예보 정보 가져오기
     */
    fun getWeatherInfo(
        dataType: String, numOfRows: Int, pageNo: Int,
        baseDate: Int, baseTime: Int, nx: String, ny: String
    ) = viewModelScope.launch {
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

        if (result.body()?.response?.body != null) {
            for (weather in result.body()!!.response.body.items.item) {
                try {
                    val gson = Gson()
                    val gsonToJson = gson.toJson(weather)
                    val gsonFromJson = gson.fromJson(gsonToJson, Weather.Item::class.java)

                    weatherInfoList.add(gsonFromJson)

                } catch (e: java.lang.Exception) {
                    Log.d("Error", e.toString())
                }
            }

            _weatherResponse.value = weatherInfoList

            Log.d("weather list", _weatherResponse.value.toString())
        }
    }

    /**
     * 날씨 예보 정보 저장하기
     */
    fun saveSelectedWeatherList() = viewModelScope.launch(Dispatchers.IO) {
        for (weather in weatherInfoList) {
            val insertWeatherInfo = SaveWeatherInfoEntity(
                0,
                weather.baseDate,
                weather.baseTime,
                weather.category,
                weather.fcstDate,
                weather.fcstTime,
                weather.fcstValue,
                weather.nx,
                weather.ny,
                false
            )
            dbRepository.insertWeatherInfo(insertWeatherInfo)
        }
    }

    fun getAllWeatherInfoData() = viewModelScope.launch {
        val list = dbRepository.getAllWeatherInfoData().asLiveData()
        selectedList = list
    }
}
