package com.example.mvvm_example.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_example.data.offerElements.OfferRepository

class OfferViewModelFactory(private val offerRepository: OfferRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OfferViewModel(offerRepository) as T
    }
}