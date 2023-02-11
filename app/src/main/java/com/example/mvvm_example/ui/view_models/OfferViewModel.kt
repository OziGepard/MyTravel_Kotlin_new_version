package com.example.mvvm_example.ui.view_models

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.mvvm_example.data.offerElements.FirebaseCallbackOffer
import com.example.mvvm_example.data.offerElements.OfferData
import com.example.mvvm_example.data.offerElements.OfferRepository
import com.example.mvvm_example.data.Place

class OfferViewModel(private val offerRepository: OfferRepository)
    : ViewModel(){

    var bundleFromActivity : Bundle? = null
    lateinit var peopleAndRooms: ArrayList<Int>
    lateinit var placeSelected: Place
    lateinit var offersList: List<OfferData>
    var dateFirst: Long? = null
    var dateSecond: Long? = null

    fun getOffers(callback: FirebaseCallbackOffer, city: String, country: String) = offerRepository.getOffers(callback, city, country)

    fun setData() {
        peopleAndRooms = bundleFromActivity!!.getIntegerArrayList("peopleAndRooms") as java.util.ArrayList<Int>
        placeSelected = if (Build.VERSION.SDK_INT >= 33) {
            bundleFromActivity!!.getParcelable("placeSelected", Place::class.java)!!
        } else {
            bundleFromActivity!!.getParcelable("placeSelected")!!
        }
        dateFirst = bundleFromActivity!!.getLong("dateFirst", 0)
        dateSecond = bundleFromActivity!!.getLong("dateSecond", 0)
    }

    companion object{
        private val TAG = "OfferViewModel"
    }
}
