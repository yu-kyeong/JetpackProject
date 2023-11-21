package com.kyeong.jetpackproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kyeong.jetpackproject.db.dao.SaveWeatherInfoDAO
import com.kyeong.jetpackproject.db.entity.DateConverters
import com.kyeong.jetpackproject.db.entity.SaveWeatherInfoEntity

@Database(entities = [SaveWeatherInfoEntity::class], version = 1)
@TypeConverters(DateConverters::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun saveWeatherInfoDAO() : SaveWeatherInfoDAO

    companion object {

        @Volatile
        private var INSTANCE : WeatherDatabase? = null

        fun getDatabase(
            context : Context
        ) : WeatherDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }


        }

    }

}