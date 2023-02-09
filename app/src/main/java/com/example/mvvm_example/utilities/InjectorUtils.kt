package com.example.mvvm_example.utilities

import com.example.mvvm_example.data.FireBaseDB
import com.example.mvvm_example.data.RoomsPeopleDao
import com.example.mvvm_example.data.TravelRepository
import com.example.mvvm_example.ui.HomeFragmentViewModel
import com.example.mvvm_example.ui.HomeFragmentViewModelFactory
import com.example.mvvm_example.ui.TravelViewModelFactory

object InjectorUtils {

    fun provideTravelViewModelFactory(): TravelViewModelFactory
    {
        val travelRepository = TravelRepository.getInstance(FireBaseDB.getInstance().travelDao)
        return TravelViewModelFactory(travelRepository)
    }

    fun provideRoomsPeopleViewModelFactory(): HomeFragmentViewModelFactory
    {
        val roomsPeople = RoomsPeopleDao.getInstance()
        return HomeFragmentViewModelFactory(roomsPeople)
    }
}