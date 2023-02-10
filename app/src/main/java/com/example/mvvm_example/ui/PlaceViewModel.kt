package com.example.mvvm_example.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_example.data.*
import java.util.*

class PlaceViewModel(

    private val placeRepository: PlaceRepository)
    :ViewModel(){

    private val TAG = "PlaceViewModel"


    fun getPlaces(callback: FirebaseCallback) = placeRepository.getPlaces(callback)

    fun getSpecificPlaces(listOfPlaces: List<Place>, textInput: String): List<Place> {
        val editedText = textInput
            .lowercase()
            .replaceFirstChar { it.titlecase(Locale.ROOT) }

        return listOfPlaces
            .filter { it.city.startsWith(editedText) || it.country.startsWith(editedText) }
    }
}