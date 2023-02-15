package com.example.mvvm_example.ui.view_models

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.mvvm_example.TravelApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragmentViewModel()
    :ViewModel(){
    private lateinit var auth: FirebaseAuth

        fun userRegister(activity: FragmentActivity, email: String, password: String)
        {
            auth = Firebase.auth
            Log.d(TAG, "$email, $password")

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(activity, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }

    companion object{
        val TAG = "RegisterViewModel"
    }
}