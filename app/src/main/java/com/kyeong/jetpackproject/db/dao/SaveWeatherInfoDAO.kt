package com.kyeong.jetpackproject.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.kyeong.jetpackproject.db.entity.SaveWeatherInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SaveWeatherInfoDAO {

    @Query("SELECT * FROM save_weather_info_table")
    fun getAllData() : Flow<List<SaveWeatherInfoEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(saveWeatherInfoEntity: SaveWeatherInfoEntity)

    @Delete
    fun remove(saveWeatherInfoEntity: SaveWeatherInfoEntity)

}