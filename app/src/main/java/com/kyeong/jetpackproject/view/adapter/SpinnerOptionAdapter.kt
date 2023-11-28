package com.kyeong.jetpackproject.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import com.kyeong.jetpackproject.databinding.ItemSpinnerOptionBinding

class SpinnerOptionAdapter(context: Context, @LayoutRes private val resId: Int, private val menuList: List<String>)
    : ArrayAdapter<String>(context, resId, menuList) {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.timeSpinner.text = menuList[position]
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.timeSpinner.text = menuList[position]
        return binding.root
    }

    override fun getCount(): Int {
        return menuList.size
    }
}