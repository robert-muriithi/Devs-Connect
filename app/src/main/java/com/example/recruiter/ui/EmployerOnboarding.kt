package com.example.recruiter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentEmployerOnboardingBinding


class EmployerOnboarding : Fragment() {

    private lateinit var binding: FragmentEmployerOnboardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerOnboardingBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_employerOnboarding_to_employerCreateAccount)
        }



        return view
    }

}