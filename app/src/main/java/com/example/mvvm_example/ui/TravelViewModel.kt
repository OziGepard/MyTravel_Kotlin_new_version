package com.example.mvvm_example.ui

import androidx.lifecycle.ViewModel
import com.example.mvvm_example.data.FirebaseCallback
import com.example.mvvm_example.data.TravelRepository

class TravelViewModel(
    private val travelRepository: TravelRepository)
    :ViewModel(){

        fun getTravels(callback: FirebaseCallback) = travelRepository.getTravels(callback)
}