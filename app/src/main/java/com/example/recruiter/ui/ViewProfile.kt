package com.example.recruiter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentViewProfileBinding

class ViewProfile : Fragment() {
    private lateinit var binding: FragmentViewProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root


        return view
    }

}