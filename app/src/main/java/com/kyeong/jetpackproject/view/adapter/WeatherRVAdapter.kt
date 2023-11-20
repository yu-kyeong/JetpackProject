package com.kyeong.jetpackproject.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kyeong.jetpackproject.R
import com.kyeong.jetpackproject.network.model.Weather

class WeatherRVAdapter(val context: Context, private val weatherList: List<Weather.Item>)
    : RecyclerView.Adapter<WeatherRVAdapter.ViewHolder>(){

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val category: TextView = view.findViewById(R.id.category)
        val fcstValue: TextView = view.findViewById(R.id.fcstValue)
        val saveBtn: ImageView = view.findViewById(R.id.saveBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherRVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_weather_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherRVAdapter.ViewHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(R.id.saveBtn).setOnClickListener { v ->
        }
        holder.category.text = weatherList[position].category
        holder.fcstValue.text = weatherList[position].fcstValue
    }

    override fun getItemCount(): Int {
        return weatherList.size

    }
}