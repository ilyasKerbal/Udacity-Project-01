package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginLayoutBinding


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : LoginLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.login_layout, container, false)
        binding.login.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToOnboardingFragment())
        }
        return binding.root
    }
}