package com.example.mvvm_example.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class TravelDao {

    private var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val travelsList = mutableListOf<Travel>()
    val TAG = "TravelDao"


    fun getTravels(firebaseCallback: FirebaseCallback)
    {
        travelsList.clear()

        db.collection("travels")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    var city = document.get("city").toString()
                    var title = document.get("title").toString()
                    var price = document.get("price").toString().toInt()
                    val travel = Travel(city, title, price)

                    travelsList.add(travel)
                }
                firebaseCallback.onCallback(travelsList as List<Travel>)
                Log.d(TAG, "$travelsList")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }
}