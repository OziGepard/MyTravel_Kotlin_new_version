package com.example.mvvm_example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_example.data.TravelRepository

class TravelViewModelFactory(private val travelRepository: TravelRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TravelViewModel(travelRepository) as T
    }
}