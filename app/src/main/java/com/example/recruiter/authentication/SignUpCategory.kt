package com.example.recruiter.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentSignUpCategoryBinding


class SignUpCategory : Fragment() {
    private lateinit var binding: FragmentSignUpCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpCategoryBinding.inflate(inflater, container,false)
        val view = binding.root

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_signUpCategory_to_signUpFragment)
        }
        binding.finalistApply.setOnClickListener {
            findNavController().navigate(R.id.action_signUpCategory_to_finalistOnboarding)
        }
        binding.employerHire.setOnClickListener{
            findNavController().navigate(R.id.action_signUpCategory_to_employerOnboarding)
        }

        return view
    }

}