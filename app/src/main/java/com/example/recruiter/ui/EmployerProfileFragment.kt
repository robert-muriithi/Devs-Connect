package com.example.recruiter.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentEmployerProfileBinding
import com.google.firebase.auth.FirebaseAuth

class EmployerProfileFragment : Fragment() {
private lateinit var binding: FragmentEmployerProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root
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



        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.item1){
            Toast.makeText(activity, "Item 1 selected", Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.item2){
            Toast.makeText(activity, "Item 2 selected", Toast.LENGTH_SHORT).show()
        }
        if (id == R.id.item3){
            Toast.makeText(activity, "Item 3 selected", Toast.LENGTH_SHORT).show()
            FirebaseAuth.getInstance().signOut()
        }
        return super.onOptionsItemSelected(item)
    }


}