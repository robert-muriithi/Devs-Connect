package com.example.recruiter.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.example.recruiter.databinding.FragmentResetPasswordBinding
import com.example.recruiter.others.CustomDialog
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



        binding.dialogConfirm.setOnClickListener {
            if (TextUtils.isEmpty(binding.userEmil.text.toString().trim())) {
                binding.progressBar6.isVisible = true
                binding.userEmil.error = "required"
                return@setOnClickListener
            }
            else
                auth.sendPasswordResetEmail(binding.userEmil.text.toString().trim()).addOnCompleteListener {
                if (it.isSuccessful) {
                    binding.progressBar6.isVisible = false
                    Toast.makeText(requireContext(), "Reset link sent", Toast.LENGTH_SHORT)
                        .show()

                }
            }.addOnFailureListener {
                    binding.progressBar6.isVisible = false
                Toast.makeText(requireContext(), "Invalid email", Toast.LENGTH_SHORT).show()

            }
        }



        return view
    }
}


