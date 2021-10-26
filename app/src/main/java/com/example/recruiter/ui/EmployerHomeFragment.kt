package com.example.recruiter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recruiter.R
import com.example.recruiter.adapter.BookmarkedContactsRecyclerAdapter
import com.example.recruiter.databinding.FragmentEmployerHomeBinding
import com.example.recruiter.model.BookmarkedContacts
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class EmployerHomeFragment : Fragment() {
    private lateinit var binding: FragmentEmployerHomeBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var myList: ArrayList<BookmarkedContacts>
    private val adapter by lazy{
        BookmarkedContactsRecyclerAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        database = FirebaseDatabase.getInstance()



        binding.marketNavImage.setOnClickListener {
            findNavController().navigate(R.id.action_employerHomeFragment_to_employerMarketplace2)
        }
        binding.chatNavImage.setOnClickListener {
            findNavController().navigate(R.id.action_employerHomeFragment_to_chatFragment2)
        }
        binding.profileNavImage.setOnClickListener {
            findNavController().navigate(R.id.action_employerHomeFragment_to_employerProfileFragment)
        }


        binding.postJob.setOnClickListener {
            findNavController().navigate(R.id.action_employerHomeFragment_to_jobPostingFragment)
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Bookmarked")
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                myList = ArrayList()
                if (snapshot.exists()){
                    for (data in snapshot.children){
                        val contacts = data.getValue(BookmarkedContacts::class.java)
                        myList.add(contacts!!)
                    }
                    adapter.submitList(myList)
                    binding.finalistRecyler1.adapter = adapter
                }
                else{
                    Log.d(TAG, "onDataChange: Failed")
                }

                val child = snapshot.children
                child.forEach {
                    var contacts = BookmarkedContacts(it.child("image").value.toString(),
                        it.child("name").value.toString(),
                        it.child("speciality").value.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled: Error occoured: ${error.toString()}")
            }

        })


        return view
    }

    /*override fun onResume() {
        super.onResume()
        val currentId = FirebaseAuth.getInstance().uid
        database!!.reference.child("presence").child(currentId!!).setValue("Online")
    }

    override fun onPause() {
        super.onPause()
        val currentId = FirebaseAuth.getInstance().uid
        database!!.reference.child("presence").child(currentId!!).setValue("Offline")
    }*/


}