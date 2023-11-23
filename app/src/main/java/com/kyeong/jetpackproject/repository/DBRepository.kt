package com.kyeong.jetpackproject.repository

import com.kyeong.jetpackproject.App
import com.kyeong.jetpackproject.db.WeatherDatabase
import com.kyeong.jetpackproject.db.entity.SaveWeatherInfoEntity

class DBRepository {

    val context = App.context()
    val db = WeatherDatabase.getDatabase(context)

    // 저장한 날씨 정보 가져오기
    fun getAllWeatherInfoData() = db.saveWeatherInfoDAO().getAllData()
    // 날씨 정보 저장
    fun insertWeatherInfo(saveWeatherInfoEntity: SaveWeatherInfoEntity) = db.saveWeatherInfoDAO().insert(saveWeatherInfoEntity)
    // 저장한 날씨 정보 삭제
    fun removeWeatherInfo(saveWeatherInfoEntity: SaveWeatherInfoEntity) = db.saveWeatherInfoDAO().remove(saveWeatherInfoEntity)

}