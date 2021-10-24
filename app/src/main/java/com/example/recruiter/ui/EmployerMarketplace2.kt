package com.example.recruiter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentEmployerMarketplace2Binding
import com.google.firebase.database.FirebaseDatabase


class EmployerMarketplace2 : Fragment() {
private lateinit var database: FirebaseDatabase
private lateinit var binding: FragmentEmployerMarketplace2Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerMarketplace2Binding.inflate(layoutInflater , container, false)
        val view = binding.root
        database = FirebaseDatabase.getInstance()

        binding.homeNavImage2.setOnClickListener {
            findNavController().navigate(R.id.action_employerMarketplace2_to_employerHomeFragment)
        }
        binding.chatNavImage2.setOnClickListener {
            findNavController().navigate(R.id.action_employerMarketplace2_to_chatFragment3)
        }
        binding.profileNavImage2.setOnClickListener {
            findNavController().navigate(R.id.action_employerMarketplace2_to_employerProfileFragment)
        }



        return view
    }

}