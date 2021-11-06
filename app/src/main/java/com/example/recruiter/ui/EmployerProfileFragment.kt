package com.example.recruiter.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentEmployerProfileBinding
import com.google.firebase.auth.FirebaseAuth

class EmployerProfileFragment : Fragment(), Toolbar.OnMenuItemClickListener,
    androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
private lateinit var binding: FragmentEmployerProfileBinding
private lateinit var nav : NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        nav = findNavController()
        binding.homeNavImage4.setOnClickListener {
           findNavController().navigate(R.id.action_employerProfileFragment_to_employerHomeFragment)
        }
        binding.marketNavImage4.setOnClickListener {
            findNavController().navigate(R.id.action_employerProfileFragment_to_employerMarketplace2)
        }
        binding.chatNavImage4.setOnClickListener {
            findNavController().navigate(R.id.action_employerProfileFragment_to_chatFragment)
        }

        binding.updateProf.setOnClickListener {
            findNavController().navigate(R.id.action_employerProfileFragment_to_empUpdateProfile)
        }

        val appBarConfiguration = AppBarConfiguration(nav.graph)
        binding.toolbar.setupWithNavController(nav,appBarConfiguration)

        binding.toolbar.setOnMenuItemClickListener(this)
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
            findNavController().navigate(R.id.action_employerProfileFragment_to_helpFragment)
            return true
        }
        else if (item?.itemId == R.id.log_out){
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_employerProfileFragment_to_loginFragment)
            Toast.makeText(requireContext(), "Logout Success", Toast.LENGTH_SHORT)
                .show()
            return true
        }
        return false
    }


}