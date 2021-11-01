package com.example.recruiter.ui

import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.recruiter.R
import com.example.recruiter.adapter.EmpMarketPlaceAdapter2
import com.example.recruiter.databinding.FragmentEmployerMarketplace2Binding
import com.example.recruiter.model.BookmarkedContacts
import com.google.firebase.database.*


class EmployerMarketplace2 : Fragment() {
private lateinit var database: FirebaseDatabase
private lateinit var binding: FragmentEmployerMarketplace2Binding
private lateinit var databaseReference : DatabaseReference
private lateinit var myList: ArrayList<BookmarkedContacts>
private lateinit var nav: NavController
private val adapter by lazy {
    EmpMarketPlaceAdapter2(EmpMarketPlaceAdapter2.OnClickListener{ item ->
        val action = EmployerMarketplace2Directions.actionEmployerMarketplace2ToViewProfile3()
        findNavController().navigate(action)
    })
}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerMarketplace2Binding.inflate(layoutInflater , container, false)
        val view = binding.root
        database = FirebaseDatabase.getInstance()

        nav = findNavController()
        val appBarConfiguration = AppBarConfiguration(nav.graph)
        binding.marketPlaceToolbar.setupWithNavController(nav,appBarConfiguration)

        binding.homeNavImage2.setOnClickListener {
            findNavController().navigate(R.id.action_employerMarketplace2_to_employerHomeFragment)
        }
        binding.chatNavImage2.setOnClickListener {
            findNavController().navigate(R.id.action_employerMarketplace2_to_chatFragment3)
        }
        binding.profileNavImage2.setOnClickListener {
            findNavController().navigate(R.id.action_employerMarketplace2_to_employerProfileFragment)
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
                    binding.marketplace2Recycler.adapter = adapter
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






}