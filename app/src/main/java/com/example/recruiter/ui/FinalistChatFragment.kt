package com.example.recruiter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.recruiter.R
import com.example.recruiter.adapter.AllChatsAdapter
import com.example.recruiter.databinding.FragmentFinalistChatBinding
import com.example.recruiter.model.Chat
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FinalistChatFragment : Fragment() {
    private lateinit var binding: FragmentFinalistChatBinding
    private lateinit var nav: NavController
    var databaseReference: DatabaseReference? = null
    var firebaseUser: FirebaseUser? = null
    private var currentUserID: String? = null
    private var adapter: AllChatsAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistChatBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        nav = findNavController()
        val appBarConfiguration = AppBarConfiguration(nav.graph)
        binding.Toolbar.setupWithNavController(nav, appBarConfiguration)

        binding.homeNavImage7.setOnClickListener {
            findNavController().navigate(R.id.action_finalistChatFragment_to_finalistHomeFragment)
        }
        binding.marketNavImage7.setOnClickListener {
            findNavController().navigate(R.id.action_finalistChatFragment_to_finalistMarketPlaceFragment)
        }
        binding.profileNavImage7.setOnClickListener {
            findNavController().navigate(R.id.action_finalistChatFragment_to_finalistProfileFragment)
        }

        firebaseUser = FirebaseAuth.getInstance().currentUser
        currentUserID = firebaseUser!!.uid
        databaseReference = FirebaseDatabase.getInstance().reference


        val mConvDatabase = databaseReference!!.child("chats").child(currentUserID!!)

        val query = mConvDatabase.orderByChild("time_stamp")

        val options: FirebaseRecyclerOptions<Chat> = FirebaseRecyclerOptions.Builder<Chat>()
            .setQuery(query, Chat::class.java)
            .build()

        adapter = AllChatsAdapter(options)
        binding.chaRecyclerView.adapter = adapter


        return view






        return view

    }

}