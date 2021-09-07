package com.example.recruiter.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.recruiter.databinding.FragmentLoginBinding
import com.example.recruiter.others.CustomDialogFragment
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private  lateinit var auth: FirebaseAuth
   // private lateinit var databaseReference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root


        auth = FirebaseAuth.getInstance()

        binding.forgotPassword.setOnClickListener {
            CustomDialogFragment().show(requireActivity().supportFragmentManager,"CustomDialog")
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.loginEmail.editText?.text.toString().trim()
            val password = binding.loginPassword.editText?.text.toString().trim()

            if (TextUtils.isEmpty(email)){
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(password)){
                return@setOnClickListener
            }
            else{
                binding.progressBar.isVisible = true
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), "Succesfully logged in", Toast.LENGTH_SHORT).show()
                    checkUserType()
                }else{
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), "Check credentials and try again", Toast.LENGTH_SHORT).show()
                }
            }
        }






        return view
    }

    private fun checkUserType() {

    }

}