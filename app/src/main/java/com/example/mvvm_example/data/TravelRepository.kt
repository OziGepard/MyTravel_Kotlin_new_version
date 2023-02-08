package com.example.mvvm_example.data

class TravelRepository private constructor(private val travelDao: TravelDao){


    fun getTravels(callback: FirebaseCallback) = travelDao.getTravels(callback)

    companion object{
        @Volatile private var instance : TravelRepository? = null

        fun getInstance(travelDao: TravelDao) =
            instance ?: synchronized(this){
                instance ?: TravelRepository(travelDao).also { instance = it }
            }
        }
    }