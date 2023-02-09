package com.example.mvvm_example.data

object RoomsPeopleEnum {

    const val ROOM = "Room"
    const val ADULT = "Adult"
    const val KID = "Kid"

    fun howManyRooms(value: Int) : String
    {
        return when(value) {
            0 -> "pokoi"
            1 -> "pokój"
            in 2..4 -> " pokoje"
            else -> " pokoi"
        }
    }

    fun howManyAdults(value: Int) : String
    {
        return when(value) {
            1 -> "dorosły"
            else -> "dorosłych"
        }
    }

    fun howManyKids(value: Int) : String
    {
        return when(value) {
            1 -> "dziecko"
            else -> "dzieci"
        }
    }

}

