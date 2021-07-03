package com.example.recruiter.authentication.employer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentEmployerCreateAccountBinding


class EmployerCreateAccount : Fragment() {
    private lateinit var binding: FragmentEmployerCreateAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerCreateAccountBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

}