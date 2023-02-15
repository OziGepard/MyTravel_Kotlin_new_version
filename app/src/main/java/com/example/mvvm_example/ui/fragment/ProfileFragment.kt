package com.example.mvvm_example.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvm_example.databinding.FragmentProfileBinding
import com.example.mvvm_example.ui.view_models.ProfileViewModel
import com.example.mvvm_example.utilities.InjectorUtils
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = InjectorUtils.provideProfileViewModelFactory()
        viewModel = ViewModelProvider(this, factory)
            .get(ProfileViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(!viewModel.userIsLoggedIn())
        {

            val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
            findNavController().navigate(action)
        }
        else
        {
            initializeUI()
        }
    }

    private fun initializeUI() {

        binding.profileSignout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            viewModel.refreshFragment(findNavController())
        }

    }

    companion object
    {
        const val TAG = "ProfileFragment"
    }
}