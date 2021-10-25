package com.example.recruiter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private lateinit var nav : NavController
    private lateinit var binding: FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHelpBinding.inflate(layoutInflater , container , false)
        val view = binding.root

        nav = findNavController()
        val appBarConfiguration = AppBarConfiguration(nav.graph)
        binding.toolbar3.setupWithNavController(nav,appBarConfiguration)

        return view
    }

}