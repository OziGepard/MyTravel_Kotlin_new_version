package com.example.mvvm_example.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(val city : String,
                 val country: String,
) : Parcelable

