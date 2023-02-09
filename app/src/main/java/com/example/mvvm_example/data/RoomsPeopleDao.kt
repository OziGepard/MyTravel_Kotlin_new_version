package com.example.mvvm_example.data

class RoomsPeopleDao {
    private val TAG = "RoomsPeopleDao"
    private var roomsPeopleData = arrayListOf(1,2,0)

    fun getRoomsPeople() = roomsPeopleData

    fun getRoomsPeopleText() : String
    {
        val rooms = roomsPeopleData[0]
        val adults = roomsPeopleData[1]
        val kids = roomsPeopleData[2]

        val roomsText = RoomsPeopleEnum.howManyRooms(rooms)
        val adultsText = RoomsPeopleEnum.howManyAdults(adults)
        val kidsText = RoomsPeopleEnum.howManyKids(kids)

        return "$rooms $roomsText | $adults $adultsText | $kids $kidsText"
    }

    fun setRoomsPeople(roomsPeopleInfo : Pair<String, Int>)
    {
        when(roomsPeopleInfo.first)
        {
            RoomsPeopleEnum.ROOM -> roomsPeopleData[0] = checkValidation(roomsPeopleInfo.first, roomsPeopleData[0], roomsPeopleInfo.second)
            RoomsPeopleEnum.ADULT -> roomsPeopleData[1] = checkValidation(roomsPeopleInfo.first, roomsPeopleData[1], roomsPeopleInfo.second)
            RoomsPeopleEnum.KID -> roomsPeopleData[2] = checkValidation(roomsPeopleInfo.first, roomsPeopleData[2], roomsPeopleInfo.second)
        }
    }

    private fun checkValidation(case : String, value : Int, addValue : Int) : Int
    {
        //------Wartość < 1 lub wartość > 30----------
        if( (value == 1 && addValue == -1) || (value == 30 && addValue == 1) )
        {
            if( (case == RoomsPeopleEnum.KID) && value == 1) return 0
            return value
        }
        //------Zwiększenie ilości pokoi -> Zwiększenie ilości dorosłych---------
        if(case == RoomsPeopleEnum.ROOM && value + addValue > roomsPeopleData[1])
        {
            roomsPeopleData[1]++
        }
        //-------Zmniejszenie ilości dorosłych -> Zmniejszenie ilości pokoi-------
        if(case == RoomsPeopleEnum.ADULT && value + addValue < roomsPeopleData[0])
        {
            roomsPeopleData[0]--
        }
        //-------Jeśli nowa liczba dzieci < 0 -> Blokada-------
        if(case == RoomsPeopleEnum.KID && (value + addValue) < 0)
        {
            return value
        }

        return value + addValue
    }


    companion object{
        @Volatile private var instance : RoomsPeopleDao? = null

        fun getInstance() =
            instance ?: synchronized(this){
                instance ?: RoomsPeopleDao().also { instance = it }
            }
    }
}