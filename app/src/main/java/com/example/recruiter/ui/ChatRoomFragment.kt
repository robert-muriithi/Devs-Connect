package com.example.recruiter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentChatRoomBinding
import com.example.recruiter.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class ChatRoomFragment : Fragment() {
private lateinit var binding: FragmentChatRoomBinding
private lateinit var databaseReference: DatabaseReference


    private var currentUserID: String? = null
    private var otherUserID: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatRoomBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        databaseReference = FirebaseDatabase.getInstance().reference

        currentUserID = FirebaseAuth.getInstance().currentUser?.uid


        return view
    }

}