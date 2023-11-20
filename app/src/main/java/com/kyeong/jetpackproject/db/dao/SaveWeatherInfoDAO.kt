package com.kyeong.jetpackproject.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kyeong.jetpackproject.db.entity.SaveWeatherInfoEntity

@Dao
interface SaveWeatherInfoDAO {

    @Query("SELECT * FROM save_weather_info_table")
    fun getAllData() : List<SaveWeatherInfoEntity>

    @Insert
    fun insert(saveWeatherInfoEntity: SaveWeatherInfoEntity)


}