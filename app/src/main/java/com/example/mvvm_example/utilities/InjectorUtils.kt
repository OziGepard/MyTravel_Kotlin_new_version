package com.example.mvvm_example.utilities

import com.example.mvvm_example.data.FireBaseDB
import com.example.mvvm_example.data.TravelRepository
import com.example.mvvm_example.ui.TravelViewModelFactory

object InjectorUtils {

    fun provideTravelViewModelFactory(): TravelViewModelFactory
    {
        val travelRepository = TravelRepository.getInstance(FireBaseDB.getInstance().travelDao)
        return TravelViewModelFactory(travelRepository)
    }
}