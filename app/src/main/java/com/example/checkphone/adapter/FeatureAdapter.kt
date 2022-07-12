package com.example.checkphone.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.checkphone.ActivityTests
import com.example.checkphone.R
import com.example.checkphone.databinding.ActivityTestsBinding
import com.example.checkphone.databinding.FeatureItemBinding
import com.example.checkphone.fragments.TestFragment
import com.example.checkphone.model.Feature

class FeatureAdapter(var activity: Activity , var data:ArrayList<Feature>):RecyclerView.Adapter<FeatureAdapter.MyViewHolder>() {

    class MyViewHolder(var binding:FeatureItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = FeatureItemBinding.inflate(LayoutInflater.from(activity) , parent ,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.txtName.text = data[position].name
        holder.binding.icon.setImageResource(data[position].icon)
        holder.binding.root.setCardBackgroundColor(getRandomColor(position))
        holder.binding.root.setOnClickListener{
            val i = Intent(activity,ActivityTests::class.java)
            i.putExtra("id",data[position].id)
            activity.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
       return data.size
    }


    private fun getRandomColor(position: Int):Int{
        when {
            position %5 == 0 -> {
                return Color.rgb(90,151,116)
            }
            position %5 == 1 -> {
                return Color.rgb(90,94,151)
            }
            position %5 == 2 -> {
                return Color.rgb(151,90,125)
            }
            position %5 == 3 -> {
                return Color.rgb(151,147,90)
            }
            position %5 == 4 ->{
                return Color.rgb(151,116,90)
            }
        }
        return Color.rgb(0,0,0)
    }



}