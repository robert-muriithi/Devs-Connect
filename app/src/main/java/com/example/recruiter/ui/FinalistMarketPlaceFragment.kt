package com.example.recruiter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentFinalistMarketPlaceBinding
import com.example.recruiter.model.FinalistMarketPlace
import com.example.recruiter.model.JobsPost
import com.google.firebase.database.*
import com.example.recruiter.adapter.FinMarketPlaceRecyclerAdapter as FinMarketPlaceRecyclerAdapter

private const val TAG = "FinalistMarketPlaceFrag"
class FinalistMarketPlaceFragment : Fragment() {
private lateinit var binding: FragmentFinalistMarketPlaceBinding
private lateinit var databaseReference: DatabaseReference
private val jobsList = ArrayList<FinalistMarketPlace>()
private val adapter: FinMarketPlaceRecyclerAdapter by lazy { FinMarketPlaceRecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistMarketPlaceBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        databaseReference = FirebaseDatabase.getInstance().getReference("Jobs_Posted")



        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i: DataSnapshot in snapshot.children){
                    val job = i.getValue(FinalistMarketPlace::class.java)
                    jobsList.add(job!!)
                }
                adapter.submitList(jobsList)
                binding.finalistMarketplaceRecycler.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "onCancelled: ", error.toException() )
            }

        }
        databaseReference.addValueEventListener(postListener)




        return  view
    }

}