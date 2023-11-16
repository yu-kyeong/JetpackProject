package com.kyeong.jetpackproject.network

import android.os.Build
import com.kyeong.jetpackproject.BuildConfig
import com.kyeong.jetpackproject.network.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("getVilageFcst?serviceKey=${BuildConfig.API_KEY}")
    suspend fun getWeatherInfo(
        @Query("dataType") dataType : String,
        @Query("numOfRows") numOfRows : Int,
        @Query("pageNo") pageNo : Int,
        @Query("base_date") baseDate : Int,
        @Query("base_time") baseTime : Int,
        @Query("nx") nx : String,
        @Query("ny") ny : String
    ) : Weather
}