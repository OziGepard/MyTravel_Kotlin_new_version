package com.example.mvvm_example.data

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_example.R
import com.example.mvvm_example.ui.PlaceViewModel
import com.example.mvvm_example.ui.fragment.SearchFragment
import com.example.mvvm_example.utilities.InjectorUtils

class PlaceAdapter(val searchFragment : SearchFragment, var dataList : List<Place>) :
    RecyclerView.Adapter<MyViewHolder>() {

    private val TAG = "PlaceAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val placeView = layoutInflater.inflate(R.layout.place_element, parent, false )

        return MyViewHolder(placeView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var country = dataList.get(holder.adapterPosition).country
        var city = dataList.get(holder.adapterPosition).city

        holder.placeCountry.text = country
        holder.placeCity.text = city

        holder.placeLayout.setOnClickListener {
            val placeSelected = Place(city, country)
            searchFragment.selectedItem(placeSelected)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updatePlaces(newPlaces: List<Place>)
    {
        dataList = newPlaces
        this.notifyDataSetChanged()
    }
}
class MyViewHolder(view : View) : RecyclerView.ViewHolder(view)
{
    val placeCountry : TextView
    val placeCity : TextView
    val placeLayout : ConstraintLayout

    init {
        placeLayout = view.findViewById(R.id.place_layout)
        placeCountry = view.findViewById(R.id.place_country)
        placeCity = view.findViewById(R.id.place_city)
    }
}