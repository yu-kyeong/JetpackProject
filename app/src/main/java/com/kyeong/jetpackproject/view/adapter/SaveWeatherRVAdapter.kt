package com.kyeong.jetpackproject.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kyeong.jetpackproject.R
import com.kyeong.jetpackproject.db.entity.SaveWeatherInfoEntity
import org.w3c.dom.Text
import java.text.SimpleDateFormat

class SaveWeatherRVAdapter(val context: Context, private val saveWeatherList: List<SaveWeatherInfoEntity>): RecyclerView.Adapter<SaveWeatherRVAdapter.ViewHolder>() {

    interface ItemClick {
        fun onClick(view : View, position: Int)
    }
    var itemClick : ItemClick? = null

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val category: TextView = view.findViewById(R.id.category)
        val fcstValue: TextView = view.findViewById(R.id.fcstValue)
        val dateValue: TextView = view.findViewById(R.id.dateAndTime)
        val deleteBtn: ImageView = view.findViewById(R.id.saveBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_weather_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.deleteBtn.setOnClickListener { v ->
            itemClick?.onClick(v, position)
        }
        holder.category.text = saveWeatherList[position].category
        holder.fcstValue.text = saveWeatherList[position].fcstValue

        setSimpleDateFormat(saveWeatherList[position], holder)
        holder.deleteBtn.setImageResource(R.drawable.ic_menu_delete)
    }

    override fun getItemCount(): Int {
        return saveWeatherList.size
    }

    @SuppressLint("SimpleDateFormat")
    fun setSimpleDateFormat(saveWeatherInfoEntity: SaveWeatherInfoEntity, holder: ViewHolder) {
        val baseDate = SimpleDateFormat("yyyyMMdd")
        val date = baseDate.parse(saveWeatherInfoEntity.baseDate.toString())
        val newDate = date?.let { SimpleDateFormat("yyyy.MM.dd").format(it) }

        holder.dateValue.apply {
            visibility = View.VISIBLE
            text = newDate
        }
    }
}