package com.example.recruiter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater,container, false)
        val view = binding.root
        binding.homeNavImage3.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_employerHomeFragment)
        }
        binding.marketNavImage3.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_employerMarketplace2)
        }
        binding.profileNavImage3.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_employerProfileFragment)
        }



        return view
    }

}