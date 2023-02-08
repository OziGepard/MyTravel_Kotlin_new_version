package com.example.mvvm_example.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_example.R

class TravelAdapter(var dataList : List<Travel>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val travelView = layoutInflater.inflate(R.layout.travel_element, parent, false )

        return MyViewHolder(travelView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var title = dataList.get(holder.adapterPosition).title
        var city = dataList.get(holder.adapterPosition).city
        var price = dataList.get(holder.adapterPosition).price

        holder.travelTitle.text = title
        holder.travelCity.text = city
        holder.travelPrice.text = price.toString() + " zł"

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
class MyViewHolder(view : View) : RecyclerView.ViewHolder(view)
{
    val travelTitle : TextView
    val travelCity : TextView
    val travelPrice : TextView

    init {
        travelTitle = view.findViewById(R.id.travel_title)
        travelCity = view.findViewById(R.id.travel_city)
        travelPrice = view.findViewById(R.id.travel_price)
    }
}