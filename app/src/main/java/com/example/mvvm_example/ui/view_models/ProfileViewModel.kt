package com.example.mvvm_example.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class ProfileViewModel
    : ViewModel(){
    private lateinit var auth: FirebaseAuth

    fun userIsLoggedIn(): Boolean {
        auth = Firebase.auth

        val currentUser = auth.currentUser
        if(currentUser != null) return true

        return false

    }

    fun refreshFragment(navController: NavController) = viewModelScope.launch {

        val id = navController.currentDestination?.id
        navController.popBackStack(id!!,true)
        navController.navigate(id)

    }
}