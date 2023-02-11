package com.example.mvvm_example.data

import com.example.mvvm_example.data.offerElements.OfferDao


class FireBaseDB private constructor() {

    var placeDao = PlaceDao()
        private set

    var offerDao = OfferDao()
        private set

    companion object{
        @Volatile private var instance : FireBaseDB? = null

        fun getInstance() =
            instance ?: synchronized(this){
                instance ?: FireBaseDB().also { instance = it }
            }
        }
    }