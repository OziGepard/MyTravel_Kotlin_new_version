package com.example.mvvm_example.data

class PlaceRepository private constructor(private val placeDao: PlaceDao){


    fun getPlaces(callback: FirebaseCallbackPlace) = placeDao.getPlaces(callback)

    companion object{
        @Volatile private var instance : PlaceRepository? = null

        fun getInstance(placeDao: PlaceDao) =
            instance ?: synchronized(this){
                instance ?: PlaceRepository(placeDao).also { instance = it }
            }
        }
    }