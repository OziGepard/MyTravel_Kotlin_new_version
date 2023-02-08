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
import com.example.mvvm_example.databinding.FragmentHomeBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var peopleAndRooms : TextInputEditText
    lateinit var dateRange : TextInputEditText
    lateinit var searchTravel : TextInputEditText
    private val calendar: Calendar = Calendar.getInstance()

    private val navOptions : NavOptions by lazy {
        NavOptions.Builder()
            .setEnterAnim(R.anim.from_right_to_left)
            .setExitAnim(R.anim.from_left_to_right)
            .setPopEnterAnim(R.anim.from_right_to_left)
            .setPopExitAnim(R.anim.from_left_to_right)
            .build()
    }

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
            showSearchFragment()
        }

        dateRange.setOnClickListener{
            showDateRangeDialog()
        }

        peopleAndRooms.setOnClickListener{
            showPeopleAndRoomsDialog()
        }
    }

    private fun showSearchFragment() {
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragment)
        val navController = navHostFragment?.findNavController()

        navController?.navigate(R.id.searchFragment,
        null,
        navOptions,
        null)
    }

    private fun showDateRangeDialog() {
        val startFrom = calendar.timeInMillis
        val constraints = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointForward.now())
            .setStart(startFrom)
            .build()

        val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Podaj datę wyjazdu")
            .setCalendarConstraints(constraints)
            .build()

        dateRangePicker.show(
            parentFragmentManager,
            "DATE PICKER"
        )
    }

    private fun showPeopleAndRoomsDialog() {
        val bottomSheetDialog = context?.let { Dialog(it) } ?: return

        bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        bottomSheetDialog.setContentView(R.layout.main_bottom_sheet_people)

        bottomSheetDialog.show()
        bottomSheetDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        bottomSheetDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialog.window?.attributes?.windowAnimations = R.style.BottomSheetAnimation
        bottomSheetDialog.window?.setGravity(Gravity.BOTTOM)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}