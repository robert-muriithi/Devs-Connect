package com.example.recruiter.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentFinalistMarketPlaceBinding
import com.example.recruiter.model.BookmarkedContacts
import com.example.recruiter.model.FinalistMarketPlace
import com.example.recruiter.model.JobsPost
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.recruiter.adapter.FinMarketPlaceRecyclerAdapter as FinMarketPlaceRecyclerAdapter

internal const val TAG = "FinalistMarketPlaceFrag"
class FinalistMarketPlaceFragment : Fragment() {
private lateinit var binding: FragmentFinalistMarketPlaceBinding
private lateinit var database: FirebaseDatabase
private lateinit var databaseReference: DatabaseReference
var job : FinalistMarketPlace? = null
private var jobsList = ArrayList<FinalistMarketPlace>()
private val adapter: FinMarketPlaceRecyclerAdapter by lazy { FinMarketPlaceRecyclerAdapter(FinMarketPlaceRecyclerAdapter.OnClickListener{

}) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistMarketPlaceBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        database = FirebaseDatabase.getInstance()
        databaseReference =  FirebaseDatabase.getInstance().getReference("Jobs_Posted")
       // databaseReference = FirebaseDatabase.getInstance().getReference("Jobs_Posted")
        binding.finHome2.setOnClickListener {
            findNavController().navigate(R.id.action_finalistMarketPlaceFragment_to_finalistHomeFragment)
        }
        binding.finChat2.setOnClickListener {
            findNavController().navigate(R.id.action_finalistMarketPlaceFragment_to_chatFragment)
        }
        binding.finProf2.setOnClickListener {
            findNavController().navigate(R.id.action_finalistMarketPlaceFragment_to_finalistProfileFragment)
        }


        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                jobsList = ArrayList()
                if (snapshot.exists()){
                    for (data in snapshot.children){
                        val contacts = data.getValue(FinalistMarketPlace::class.java)
                        jobsList.add(contacts!!)
                    }
                    adapter.submitList(jobsList)
                    binding.finalistMarketplaceRecycler.adapter = adapter
                }
                else{
                    Log.d(TAG, "onDataChange: Failed")
                }

                val child = snapshot.children
                child.forEach {
                    var contacts = FinalistMarketPlace(it.child("jobTitle").value.toString(),
                    it.child("jobRole").value.toString(),
                        it.child("jobDesc").value.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled: Error occoured: ${error.toString()}")
            }

        })

        return  view
    }


}