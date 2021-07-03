package com.example.recruiter.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentFinalistCreateAccountBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentFinalistCreateAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistCreateAccountBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }


}