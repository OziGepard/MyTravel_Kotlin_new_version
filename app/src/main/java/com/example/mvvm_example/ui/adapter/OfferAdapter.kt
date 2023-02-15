package com.example.mvvm_example.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_example.R

class OfferAdapter(var offerDataList : List<OfferData>) :
    RecyclerView.Adapter<OfferHolder>() {

    private val TAG = "OfferAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val offerView = layoutInflater.inflate(R.layout.offer_element, parent, false )

        return OfferHolder(offerView)
    }

    override fun onBindViewHolder(holder: OfferHolder, position: Int) {

        val title = offerDataList[position].title
        val price = offerDataList[position].price.toString()
//        val image = offerDataList[position].image

        holder.offerTitle.text = title
        holder.offerPrice.text = price
    }

    override fun getItemCount(): Int {
        return offerDataList.size
    }
}
class OfferHolder(view : View) : RecyclerView.ViewHolder(view)
{
    val offerTitle: TextView
    val offerPrice: TextView
    val offerImage: ImageView

    init {
        offerTitle = view.findViewById(R.id.offer_title)
        offerPrice = view.findViewById(R.id.offer_price)
        offerImage = view.findViewById(R.id.offer_image)
    }

}