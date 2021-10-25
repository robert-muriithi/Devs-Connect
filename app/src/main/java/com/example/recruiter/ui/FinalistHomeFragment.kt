package com.example.recruiter.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recruiter.R
import com.example.recruiter.adapter.FinalistHomeAdapter
import com.example.recruiter.databinding.FragmentFinalistHomeBinding
import com.example.recruiter.model.CompanyInfor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class FinalistHomeFragment : Fragment() {
private lateinit var binding: FragmentFinalistHomeBinding
private lateinit var database: FirebaseDatabase
private lateinit var databaseReference: DatabaseReference
private var companyList = ArrayList<CompanyInfor>()
private val adapter by  lazy { FinalistHomeAdapter(FinalistHomeAdapter.OnClickListener{
    //navigate
}) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistHomeBinding.inflate(layoutInflater,container, false)
        val view = binding.root
        val layoutManager = LinearLayoutManager(requireContext())
        binding.finalistRecycler.layoutManager = layoutManager

        binding.finMarket1.setOnClickListener {
            findNavController().navigate(R.id.action_finalistHomeFragment_to_finalistMarketPlaceFragment2)
        }
        binding.finChat1.setOnClickListener {
            findNavController().navigate(R.id.action_finalistHomeFragment_to_chatFragment)
        }
        binding.finProf1.setOnClickListener {
            findNavController().navigate(R.id.action_finalistHomeFragment_to_finalistProfileFragment)
        }

       database = FirebaseDatabase.getInstance()

        databaseReference = FirebaseDatabase.getInstance().getReference("Company information")
        databaseReference.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                companyList = ArrayList()
                if (snapshot.exists()){
                    Log.e(TAG, "onDataChange: Exist", )
                    for (data in snapshot.children){
                        val companies = data.getValue(CompanyInfor::class.java)
                        companyList.add(companies!!)
                    }
                    adapter.submitList(companyList)
                    binding.finalistRecycler.adapter = adapter
                }
                else{
                    Log.e(TAG, "onDataChange: Failed")
                }

                val child = snapshot.children
                child.forEach {
                    var company = CompanyInfor(it.child("compName").value.toString(),
                        it.child("compAbout").value.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled: Error occured: ${error.message}")
            }

        })


        return view
    }


}