package com.example.recruiter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentEmployerHomeBinding
import com.google.firebase.database.DatabaseReference


class EmployerHomeFragment : Fragment() {
    private lateinit var binding: FragmentEmployerHomeBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerHomeBinding.inflate(inflater,container,false)
        val view = binding.root


        binding.postJob.setOnClickListener {
            findNavController().navigate(R.id.action_employerHomeFragment_to_jobPostingFragment)
        }



        return view
    }

}