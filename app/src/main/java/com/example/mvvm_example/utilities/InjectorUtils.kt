package com.example.mvvm_example.utilities

import com.example.mvvm_example.data.FireBaseDB
import com.example.mvvm_example.data.RoomsPeopleDao
import com.example.mvvm_example.data.PlaceRepository
import com.example.mvvm_example.ui.HomeFragmentViewModelFactory
import com.example.mvvm_example.ui.PlaceViewModelFactory

object InjectorUtils {

    fun providePlaceViewModelFactory(): PlaceViewModelFactory
    {
        val placeRepository = PlaceRepository.getInstance(FireBaseDB.getInstance().placeDao)
        return PlaceViewModelFactory(placeRepository)
    }

    fun provideRoomsPeopleViewModelFactory(): HomeFragmentViewModelFactory
    {
        val roomsPeople = RoomsPeopleDao.getInstance()
        return HomeFragmentViewModelFactory(roomsPeople)
    }
}