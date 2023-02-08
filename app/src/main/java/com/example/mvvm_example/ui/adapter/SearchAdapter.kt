package com.example.mvvm_example.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_example.R
import com.example.mvvm_example.data.SearchData

class SearchAdapter(private val searchList : MutableList<SearchData>) : RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val searchView = layoutInflater.inflate(R.layout.search_item_recyclerview, parent, false)

        return SearchViewHolder(searchView)

    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

    }
}

class SearchViewHolder(view : View) : RecyclerView.ViewHolder(view)
{
    private val searchItemCountryCity : TextView

    init {
        searchItemCountryCity = view.findViewById(R.id.search_item_country_city)
    }

}