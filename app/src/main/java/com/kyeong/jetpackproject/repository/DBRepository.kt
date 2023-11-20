package com.kyeong.jetpackproject.repository

import com.kyeong.jetpackproject.App
import com.kyeong.jetpackproject.db.WeatherDatabase
import com.kyeong.jetpackproject.db.entity.SaveWeatherInfoEntity

class DBRepository {

    val context = App.context()
    val db = WeatherDatabase.getDatabase(context)

    // 전체 날씨 정보 가져오기
    fun getAllWeatherInfoData() = db.saveWeatherInfoDAO().getAllData()

    fun insertWeatherInfo(saveWeatherInfoEntity: SaveWeatherInfoEntity) = db.saveWeatherInfoDAO().insert(saveWeatherInfoEntity)


}