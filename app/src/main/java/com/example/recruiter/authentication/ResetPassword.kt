package com.example.recruiter.authentication

import android.os.Bundle
import android.os.PatternMatcher
import android.text.TextUtils
import android.util.Patterns.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentResetPasswordBinding
import com.example.recruiter.others.CustomDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.installations.Utils
import java.util.regex.Pattern

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


            auth.sendPasswordResetEmail(mail).addOnCompleteListener {
                if (it.isSuccessful){

                    val alert = CustomDialog()
                    alert.showResetPasswordDialog(activity)
                    //Toast.makeText(requireContext(), "Check your Email for reset link", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(), "Email does not exist", Toast.LENGTH_SHORT).show()
                }
            }
        }



        return view
    }
}


