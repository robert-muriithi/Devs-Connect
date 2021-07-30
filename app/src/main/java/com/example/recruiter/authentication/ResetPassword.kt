package com.example.recruiter.authentication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentResetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ResetPassword : DialogFragment() {

    private lateinit var binding: FragmentResetPasswordBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = FirebaseAuth.getInstance()

        val mail = binding.emailReset.editText?.text.toString().trim()

        binding.emailReset.setOnClickListener {
            if (TextUtils.isEmpty(mail)){
                return@setOnClickListener
            }
        }



        return view
    }
}