package com.example.mvvm_example.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class PlaceDao {

    private var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val placesList = mutableListOf<Place>()
    val TAG = "TravelDao"


    fun getTravels(firebaseCallback: FirebaseCallback)
    {
        placesList.clear()

        db.collection("available_places")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    var city = document.get("city").toString()
                    var country = document.get("country").toString()

                    val places = Place(city, country)

                    placesList.add(places)
                }
                firebaseCallback.onCallback(placesList)
                Log.d(TAG, "$placesList")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }
}