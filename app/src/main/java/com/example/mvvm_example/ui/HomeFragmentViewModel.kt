package com.example.mvvm_example.ui

import androidx.lifecycle.ViewModel
import com.example.mvvm_example.data.RoomsPeopleDao

class HomeFragmentViewModel(private val roomsPeople : RoomsPeopleDao)
    :ViewModel(){

        fun getRoomsPeople() = roomsPeople.getRoomsPeople()
        fun getRoomsPeopleText() = roomsPeople.getRoomsPeopleText()
        fun setRoomsPeople(roomsPeopleInfo : Pair<String, Int>) = roomsPeople.setRoomsPeople(roomsPeopleInfo)
}