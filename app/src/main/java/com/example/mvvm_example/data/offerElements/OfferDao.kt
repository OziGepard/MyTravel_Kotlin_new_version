package com.example.mvvm_example.data.offerElements

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class OfferDao {

    private var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private var offerList = listOf<OfferData>()
    val TAG = "OfferDao"


    fun getOffers(firebaseCallbackOffer: FirebaseCallbackOffer, city: String, country: String)
    {
        val tempPlaceList = mutableListOf<OfferData>()

        Log.d(TAG, "Country: $country , City: $city")
        db.collection("offers")
            .whereEqualTo("country", country)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val offerObject = document.toObject(OfferData::class.java)
                    tempPlaceList.add(offerObject)

                }
                offerList = tempPlaceList
                firebaseCallbackOffer.onCallbackOffer(offerList)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }
}