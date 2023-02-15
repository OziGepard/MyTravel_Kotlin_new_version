package com.example.mvvm_example.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mvvm_example.R

class ReservationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fm: FragmentManager? = fragmentManager

        for (entry in 0 until fm!!.backStackEntryCount) {
            Log.i(TAG, "Found fragment: " + fm.getBackStackEntryAt(entry).getId())
        }
        return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

    companion object {

        const val TAG= "ReservationFragment"
    }
}