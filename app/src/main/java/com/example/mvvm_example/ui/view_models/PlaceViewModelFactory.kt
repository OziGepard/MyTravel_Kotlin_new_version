package com.example.mvvm_example.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_example.data.PlaceRepository

class PlaceViewModelFactory(private val placeRepository: PlaceRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaceViewModel(placeRepository) as T
    }
}