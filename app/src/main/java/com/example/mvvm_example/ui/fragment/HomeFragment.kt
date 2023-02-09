package com.example.mvvm_example.ui.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.mvvm_example.R
import com.example.mvvm_example.data.HomeFragmentFunctions
import com.example.mvvm_example.databinding.FragmentHomeBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class HomeFragment : Fragment(), HomeFragmentFunctions {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var peopleAndRooms : TextInputEditText
    private lateinit var dateRange : TextInputEditText
    private lateinit var searchTravel : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUI()
    }

    private fun initializeUI() {
        peopleAndRooms = binding.peopleTravel
        dateRange = binding.dateRangeTravel
        searchTravel = binding.searchTravel

        searchTravel.setOnClickListener{
            showSearchFragment(activity)
        }

        dateRange.setOnClickListener{
            showDateRangeDialog(parentFragmentManager)
        }

        peopleAndRooms.setOnClickListener{
            showPeopleAndRoomsDialog(context)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}