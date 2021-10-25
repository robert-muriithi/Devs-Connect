package com.example.recruiter.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentFinalistProfileBinding
import com.google.firebase.auth.FirebaseAuth


class FinalistProfileFragment : Fragment(), Toolbar.OnMenuItemClickListener,
    androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
private lateinit var binding: FragmentFinalistProfileBinding
private lateinit var nav : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        nav = findNavController()

        binding.finHome4.setOnClickListener {
            findNavController().navigate(R.id.action_finalistProfileFragment_to_finalistHomeFragment)
        }
        binding.finMarket4.setOnClickListener {
            findNavController().navigate(R.id.action_finalistProfileFragment_to_finalistMarketPlaceFragment)
        }
        binding.finChat4.setOnClickListener {
            findNavController().navigate(R.id.action_finalistProfileFragment_to_chatFragment)
        }
        binding.materialEditButton.setOnClickListener {
            findNavController().navigate(R.id.action_finalistProfileFragment_to_finalistUpdateProfile)
        }
        val appBarConfiguration = AppBarConfiguration(nav.graph)
        binding.toolbar2.setupWithNavController(nav,appBarConfiguration)
        binding.toolbar2.setOnMenuItemClickListener(this)


        return view
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.about){
            val url = "https://github.com/robert-muriithi"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
            return true
        }else if (item?.itemId == R.id.help){
            findNavController().navigate(R.id.action_finalistProfileFragment_to_helpFragment)
            return true
        }
        else if (item?.itemId == R.id.log_out){
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_finalistProfileFragment_to_loginFragment)
            Toast.makeText(requireContext(), "Logout Success", Toast.LENGTH_SHORT)
                .show()
            return true
        }
        return false
    }

}