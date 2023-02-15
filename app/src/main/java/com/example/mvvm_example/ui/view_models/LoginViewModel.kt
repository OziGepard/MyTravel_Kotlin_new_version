package com.example.mvvm_example.ui.view_models

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_example.data.LoginCallback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel
    : ViewModel(){
    private lateinit var auth: FirebaseAuth

    fun logIn(loginCallback: LoginCallback,
              fragment: FragmentActivity,
              email: String,
              password: String) = viewModelScope.launch {
        auth = Firebase.auth

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(fragment) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    loginCallback.onCallbackLogin(true)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(fragment, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    loginCallback.onCallbackLogin(false)
                }
            }
    }
    companion object{
        const val TAG = "LoginViewModel"
    }
}