package com.example.mvvm_example.data


class FireBaseDB private constructor() {

    var travelDao = TravelDao()
        private set

    companion object{
        @Volatile private var instance : FireBaseDB? = null

        fun getInstance() =
            instance ?: synchronized(this){
                instance ?: FireBaseDB().also { instance = it }
            }
        }
    }