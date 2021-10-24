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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentFinalistMarketPlaceBinding
import com.example.recruiter.model.FinalistMarketPlace
import com.example.recruiter.model.JobsPost
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.recruiter.adapter.FinMarketPlaceRecyclerAdapter as FinMarketPlaceRecyclerAdapter

internal const val TAG = "FinalistMarketPlaceFrag"
class FinalistMarketPlaceFragment : Fragment() {
private lateinit var binding: FragmentFinalistMarketPlaceBinding
private lateinit var database: FirebaseDatabase
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
       // databaseReference = FirebaseDatabase.getInstance().getReference("Jobs_Posted")
        val layoutManager = LinearLayoutManager(requireContext())
        binding.finalistMarketplaceRecycler.layoutManager = layoutManager
        database.reference.child("Jobs_Posted")
            .addValueEventListener(object  : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    job  = snapshot.getValue(FinalistMarketPlace::class.java)
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
                }

            })
        adapter.submitList(jobsList)
        binding.finalistMarketplaceRecycler.adapter = adapter

        database.reference.child("Jobs_Posted").addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                jobsList!!.clear()
                for (snapshot1 in snapshot.children){
                    val jobsPost : FinalistMarketPlace? = snapshot1.getValue(FinalistMarketPlace::class.java)
                    if (jobsPost!!.id != FirebaseAuth.getInstance().uid) jobsList!!.add(jobsPost)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }

        })

/*
        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //binding.finMarketPlaceProgressBar.isVisible = true
                for (i: DataSnapshot in snapshot.children){
                    val job = i.getValue(FinalistMarketPlace::class.java)
                    jobsList.add(job!!)
                }
                adapter.submitList(jobsList)
                binding.finalistMarketplaceRecycler.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
              //  binding.finMarketPlaceProgressBar.isVisible = false
                Log.e(TAG, "onCancelled: ", error.toException() )
            }

        }
        databaseReference.addValueEventListener(postListener)*/


        return  view
    }


}