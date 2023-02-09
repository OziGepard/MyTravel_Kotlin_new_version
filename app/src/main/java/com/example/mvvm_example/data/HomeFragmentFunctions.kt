package com.example.mvvm_example.data

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.mvvm_example.R
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

interface HomeFragmentFunctions {
    private val calendar: Calendar
        get() = Calendar.getInstance()

    fun showSearchFragment(activity : FragmentActivity?) {
        val navOptions : NavOptions = NavOptions.Builder()
            .setExitAnim(R.anim.from_right_to_left_faster)
            .setEnterAnim(R.anim.from_right_to_left)
            .setPopEnterAnim(R.anim.from_left_to_right_pop)
            .setPopExitAnim(R.anim.from_left_to_right_pop_faster)
            .build()
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragment)
        val navController = navHostFragment?.findNavController()

        navController?.navigate(
            R.id.searchFragment,
            null,
            navOptions,
            null)
    }

    fun showDateRangeDialog(parentFragmentManager : FragmentManager) {
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

    fun showPeopleAndRoomsDialog(context : Context?) {
        val bottomSheetDialog = context?.let { Dialog(it) } ?: return

        bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        bottomSheetDialog.setContentView(R.layout.main_bottom_sheet_people)

        bottomSheetDialog.show()
        bottomSheetDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        bottomSheetDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialog.window?.attributes?.windowAnimations = R.style.BottomSheetAnimation
        bottomSheetDialog.window?.setGravity(Gravity.BOTTOM)

    }
}