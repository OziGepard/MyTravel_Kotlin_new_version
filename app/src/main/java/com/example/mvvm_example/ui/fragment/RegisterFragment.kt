package com.example.mvvm_example.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_example.R
import com.example.mvvm_example.databinding.FragmentRegisterBinding
import com.example.mvvm_example.ui.view_models.LoginViewModel
import com.example.mvvm_example.ui.view_models.RegisterFragmentViewModel
import com.example.mvvm_example.utilities.InjectorUtils

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegisterFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = InjectorUtils.provideRegisterViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(RegisterFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerFragButton.setOnClickListener{
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()

            activity?.let { it1 -> viewModel.userRegister(it1,email, password) }
        }

    }
}