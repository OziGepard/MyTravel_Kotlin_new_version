package com.example.mvvm_example.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_example.R
import com.example.mvvm_example.data.offerElements.OfferData

class OfferAdapter(var offerDataList : List<OfferData>) :
    RecyclerView.Adapter<OfferHolder>() {

    private val TAG = "OfferAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val offerView = layoutInflater.inflate(R.layout.offer_element, parent, false )

        return OfferHolder(offerView)
    }

    override fun onBindViewHolder(holder: OfferHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return offerDataList.size
    }

    fun updateOffers(newOfferData: List<OfferData>)
    {
        offerDataList = newOfferData
        this.notifyDataSetChanged()
    }
}
class OfferHolder(view : View) : RecyclerView.ViewHolder(view)
{

}