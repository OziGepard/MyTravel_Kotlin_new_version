package com.example.mvvm_example.data

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.core.util.Pair
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.mvvm_example.R
import com.example.mvvm_example.ui.fragment.HomeFragmentDirections
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

interface HomeFragmentFunctions {
    private val calendar: Calendar
        get() = Calendar.getInstance()

    fun showDateRangeDialog(): MaterialDatePicker<Pair<Long, Long>> {
        val startFrom = calendar.timeInMillis
        val locale = Locale("pl", "PL")
        val format = SimpleDateFormat("dd-MM-yyyy")
        val constraints = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointForward.now())
            .setStart(startFrom)
            .build()

        return MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Podaj datę wyjazdu")
            .setTextInputFormat(format)
            .setCalendarConstraints(constraints)
            .build()
    }

    fun createPeopleAndRoomsDialog(context : Context?) : Dialog? {
        val bottomSheetDialog = context?.let { Dialog(it) } ?: return null

        bottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        bottomSheetDialog.setContentView(R.layout.main_bottom_sheet_people)

        bottomSheetDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        bottomSheetDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialog.window?.attributes?.windowAnimations = R.style.BottomSheetAnimation
        bottomSheetDialog.window?.setGravity(Gravity.BOTTOM)

        return bottomSheetDialog
    }
}