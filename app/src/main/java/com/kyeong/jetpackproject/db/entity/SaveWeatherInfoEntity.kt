package com.kyeong.jetpackproject.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "save_weather_info_table")
data class SaveWeatherInfoEntity(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val baseDate : Int,
    val baseTime : Int,
    val category : String,
    val fcstDate : Int,
    val fcstTime : Int,
    val fcstValue : String,
    val nx : Int,
    val ny : Int,
    val selected : Boolean

)