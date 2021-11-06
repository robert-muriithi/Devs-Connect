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
import com.bumptech.glide.Glide
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentFinalistProfileBinding
import com.example.recruiter.model.RegisteredFinalist
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class FinalistProfileFragment : Fragment(), Toolbar.OnMenuItemClickListener,
    androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
    private lateinit var binding: FragmentFinalistProfileBinding
    private lateinit var nav: NavController
    private lateinit var databaseReference: DatabaseReference
    private lateinit var uid: String
    private var registeredFinalist = ArrayList<RegisteredFinalist>()
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()
        nav = findNavController()
        registeredFinalist = ArrayList()

        binding.finHome4.setOnClickListener {
            findNavController().navigate(R.id.action_finalistProfileFragment_to_finalistHomeFragment)
        }
        binding.finMarket4.setOnClickListener {
            findNavController().navigate(R.id.action_finalistProfileFragment_to_finalistMarketPlaceFragment)
        }
        binding.finChat4.setOnClickListener {
            findNavController().navigate(R.id.action_finalistProfileFragment_to_finalistChatFragment)
        }
        binding.materialEditButton.setOnClickListener {
            findNavController().navigate(R.id.action_finalistProfileFragment_to_finalistUpdateProfile)
        }
        val appBarConfiguration = AppBarConfiguration(nav.graph)
        binding.toolbar2.setupWithNavController(nav, appBarConfiguration)
        binding.toolbar2.setOnMenuItemClickListener(this)

        databaseReference =
            FirebaseDatabase.getInstance().getReference("Developers Profile Details")

        fetchData()


        return view
    }

    private fun fetchData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val finalist = data.getValue(RegisteredFinalist::class.java)
                        registeredFinalist.add(finalist!!)
                        binding.profName.text = finalist.name
                        binding.profSpeciality.text = finalist.speciality
                        binding.profAbout.text = finalist.about
                        Glide.with(binding.profImage)
                            .load(finalist.imageUrl)
                            .into(binding.profImage)
                        /*registeredFinalist = snapshot.getValue(RegisteredFinalist::class.java)!!
                        binding.profName.text = registeredFinalist.name
                        binding.profSpeciality.text = registeredFinalist.speciality
                        binding.profAbout.text = registeredFinalist.about
                        Glide.with(binding.profImage)
                            .load(registeredFinalist.imageUrl)
                            .into(binding.profImage)*/
                    }

                } else {
                    Toast.makeText(requireContext(), "Nothing", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.about) {
            val url = "https://github.com/robert-muriithi"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
            return true
        } else if (item?.itemId == R.id.help) {
            findNavController().navigate(R.id.action_finalistProfileFragment_to_helpFragment)
            return true
        } else if (item?.itemId == R.id.log_out) {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_finalistProfileFragment_to_loginFragment)
            Toast.makeText(requireContext(), "Logout Success", Toast.LENGTH_SHORT)
                .show()
            return true
        }
        return false
    }

}