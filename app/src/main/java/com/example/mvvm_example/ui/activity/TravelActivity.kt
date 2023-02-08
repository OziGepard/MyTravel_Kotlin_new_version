package com.example.mvvm_example.ui.activity

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_example.R
import com.example.mvvm_example.data.FirebaseCallback
import com.example.mvvm_example.data.Travel
import com.example.mvvm_example.data.TravelAdapter
import com.example.mvvm_example.databinding.ActivityTravelBinding
import com.example.mvvm_example.ui.TravelViewModel
import com.example.mvvm_example.utilities.InjectorUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.checkerframework.common.subtyping.qual.Bottom


class TravelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTravelBinding
    val TAG = "TravelActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTravelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUI()

    }

    private fun initializeUI() {
        val bottomNavigation = binding.bottomNavigation
        val navController = findNavController(R.id.fragment)

        NavigationUI.setupWithNavController(bottomNavigation, navController)


//        val recyclerView = findViewById<RecyclerView>(R.id.travel_recyclerview)
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


//        val factory = InjectorUtils.provideTravelViewModelFactory()
//        val viewModel = ViewModelProvider(this, factory)
//            .get(TravelViewModel::class.java)
//
//        viewModel.getTravels(object : FirebaseCallback {
//            override fun onCallback(list: List<Travel>) {
//                Log.d(TAG, list.toString())
//
//                val adapter = TravelAdapter(list)
//                recyclerView.adapter = adapter
//                recyclerView.setHasFixedSize(false)
//
//            }
//        })
    }

}