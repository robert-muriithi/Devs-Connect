package com.example.recruiter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentEmployerProfileBinding

class EmployerProfileFragment : Fragment() {
private lateinit var binding: FragmentEmployerProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        binding.homeNavImage4.setOnClickListener {
           findNavController().navigate(R.id.action_employerProfileFragment_to_employerHomeFragment)
        }
        binding.marketNavImage4.setOnClickListener {
            findNavController().navigate(R.id.action_employerProfileFragment_to_employerMarketplace2)
        }
        binding.chatNavImage4.setOnClickListener {
            findNavController().navigate(R.id.action_employerProfileFragment_to_chatFragment)
        }

        binding.updateProf.setOnClickListener {
            findNavController().navigate(R.id.action_employerProfileFragment_to_empUpdateProfile)
        }


        return view
    }

}