package com.example.mvvm_example.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_example.data.RoomsPeopleDao

class HomeFragmentViewModelFactory(private val roomsPeople : RoomsPeopleDao)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(roomsPeople) as T
    }
}