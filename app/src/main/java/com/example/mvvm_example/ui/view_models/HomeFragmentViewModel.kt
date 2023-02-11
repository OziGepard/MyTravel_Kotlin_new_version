package com.example.mvvm_example.ui.view_models

import androidx.lifecycle.ViewModel
import com.example.mvvm_example.data.Place
import com.example.mvvm_example.data.RoomsPeopleDao
import androidx.core.util.Pair as APair

class HomeFragmentViewModel(private val roomsPeople : RoomsPeopleDao)
    :ViewModel(){

    val readyToSearch = mutableListOf(false, false)

    var placeSelected : Place? = null
    var dateSelected: APair<Long, Long>? = null

        fun getRoomsPeople() = roomsPeople.getRoomsPeople()
        fun getRoomsPeopleText() = roomsPeople.getRoomsPeopleText()
        fun setRoomsPeople(roomsPeopleInfo : Pair<String, Int>) = roomsPeople.setRoomsPeople(roomsPeopleInfo)

    fun isReadyToSearch() : Boolean
    {
        readyToSearch.forEach {
            if (!it) return false
        }
        return true
    }
}