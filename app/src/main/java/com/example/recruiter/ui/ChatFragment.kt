package com.example.recruiter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.adapter.AllChatsAdapter
import com.example.recruiter.databinding.FragmentChatBinding
import com.example.recruiter.model.Chat
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ChatFragment : Fragment() {
private lateinit var binding: FragmentChatBinding

    private var adapter: AllChatsAdapter? = null
    var databaseReference: DatabaseReference? = null
    var firebaseUser: FirebaseUser? = null
    private var currentUserID: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater,container, false)
        val view = binding.root
        binding.homeNavImage3.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_employerHomeFragment)
        }
        binding.marketNavImage3.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_employerMarketplace2)
        }
        binding.profileNavImage3.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_employerProfileFragment)
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
        binding.chatsRecyclerView.adapter = adapter


        return view
    }
    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }

}