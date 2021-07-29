package com.example.recruiter.authentication.employer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentEmployerCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth


class EmployerCreateAccount : Fragment() {
    private lateinit var binding: FragmentEmployerCreateAccountBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerCreateAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = FirebaseAuth.getInstance()

        binding.returnToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_employerCreateAccount_to_signUpFragment)
        }

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_employerCreateAccount_to_companyInformation)
        }




        return view
    }

}