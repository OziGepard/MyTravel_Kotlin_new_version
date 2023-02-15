package com.example.mvvm_example.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvm_example.data.LoginCallback
import com.example.mvvm_example.databinding.FragmentLoginBinding
import com.example.mvvm_example.ui.view_models.LoginViewModel
import com.example.mvvm_example.utilities.InjectorUtils

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = InjectorUtils.provideLoginViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUI()

    }

    private fun initializeUI() {

        binding.registerButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            activity?.let { it1 -> viewModel.logIn(object : LoginCallback {
                override fun onCallbackLogin(isLogged: Boolean) {
                    if(isLogged)
                    {
                        val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment()
                        findNavController().navigate(action)
                    }
                }
            }, it1,email, password) }


        }
    }



    companion object
    {
        const val TAG = "LoginFragment"
    }
}