package com.example.mvvm_example.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.util.Pair
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.mvvm_example.R
import com.example.mvvm_example.data.HomeFragmentFunctions
import com.example.mvvm_example.databinding.FragmentHomeBinding
import com.example.mvvm_example.ui.HomeFragmentViewModel
import com.example.mvvm_example.utilities.InjectorUtils
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class HomeFragment : Fragment(), HomeFragmentFunctions, View.OnClickListener {

    private val TAG = "HomeFragment"
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var peopleAndRooms: TextInputEditText
    private lateinit var dateRange: TextInputEditText
    private lateinit var searchTravel: TextInputEditText
    private lateinit var viewModel: HomeFragmentViewModel

    private var textRoom: TextView? = null
    private var textAdult: TextView? = null
    private var textKid: TextView? = null

    private var dialog: Dialog? = null
    private var calendar: MaterialDatePicker<Pair<Long, Long>>? = null


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
        val locale = Locale("pl", "PL")
        Locale.setDefault(locale)

        //-------ViewModel dla HomeFragment----------
        val factory = InjectorUtils.provideRoomsPeopleViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(HomeFragmentViewModel::class.java)


        searchTravel.setOnClickListener {
            showSearchFragment(activity)
        }


        dateRange.setOnClickListener {

            calendar = calendar ?: showDateRangeDialog()
            calendar!!.addOnPositiveButtonClickListener {
                dateRange.setText(calendar!!.headerText)
            }

            calendar!!.show(
                parentFragmentManager,
                "DATE PICKER"
            )

        }

        peopleAndRooms.setOnClickListener {

            dialog = dialog ?: createPeopleAndRoomsDialog(context).apply {

                buttonsListening(this)

                this?.setOnDismissListener {
                    peopleAndRooms.setText(viewModel.getRoomsPeopleText())
                }
            }
            dialog?.show()
        }

    }

    override fun onClick(view: View?) {
        Log.d(TAG, "ON CLICK: $viewModel")
        when (view?.id) {
            R.id.button_inc_room -> {
                viewModel.setRoomsPeople("Room" to 1)
            }
            R.id.button_dec_room -> {
                viewModel.setRoomsPeople("Room" to -1)
            }
            R.id.button_inc_adult -> {
                viewModel.setRoomsPeople("Adult" to 1)
            }
            R.id.button_dec_adult -> {
                viewModel.setRoomsPeople("Adult" to -1)
            }
            R.id.button_inc_kid -> {
                viewModel.setRoomsPeople("Kid" to 1)
            }
            R.id.button_dec_kid -> {
                viewModel.setRoomsPeople("Kid" to -1)
            }
        }
        val values = viewModel.getRoomsPeople()
        textRoom?.text = values[0].toString()
        textAdult?.text = values[1].toString()
        textKid?.text = values[2].toString()
    }

    private fun buttonsListening(dialog : Dialog?)
    {
        val buttonIncRoom = dialog?.findViewById<Button>(R.id.button_inc_room)
        val buttonDecRoom = dialog?.findViewById<Button>(R.id.button_dec_room)
        val buttonIncAdult = dialog?.findViewById<Button>(R.id.button_inc_adult)
        val buttonDecAdult = dialog?.findViewById<Button>(R.id.button_dec_adult)
        val buttonIncKid = dialog?.findViewById<Button>(R.id.button_inc_kid)
        val buttonDecKid = dialog?.findViewById<Button>(R.id.button_dec_kid)

        textRoom = dialog?.findViewById(R.id.text_room)
        textAdult = dialog?.findViewById(R.id.text_adult)
        textKid = dialog?.findViewById(R.id.text_kid)

        buttonIncRoom?.setOnClickListener(this@HomeFragment)
        buttonDecRoom?.setOnClickListener(this@HomeFragment)
        buttonIncAdult?.setOnClickListener(this@HomeFragment)
        buttonDecAdult?.setOnClickListener(this@HomeFragment)
        buttonIncKid?.setOnClickListener(this@HomeFragment)
        buttonDecKid?.setOnClickListener(this@HomeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
