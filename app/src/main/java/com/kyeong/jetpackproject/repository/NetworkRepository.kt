package com.kyeong.jetpackproject.repository

import com.kyeong.jetpackproject.network.Api
import com.kyeong.jetpackproject.network.RetrofitInstance
import com.kyeong.jetpackproject.network.model.Weather
import retrofit2.Response

class NetworkRepository {

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getWeatherInfo(
        dataType : String, numOfRows : Int, pageNo : Int,
        baseDate : Int, baseTime : Int, nx : String, ny : String) : Response<Weather> {
        return client.getWeatherInfo(dataType,numOfRows,pageNo,baseDate,baseTime,nx,ny)
    }
}