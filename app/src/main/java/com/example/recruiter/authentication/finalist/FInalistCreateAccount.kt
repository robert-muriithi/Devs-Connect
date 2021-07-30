package com.example.recruiter.authentication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentFinalistCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentFinalistCreateAccountBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistCreateAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = FirebaseAuth.getInstance()
        binding.return2Login.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)

        }

        binding.registerBtn.setOnClickListener {
            val full_name = binding.finalistFullName.editText?.text.toString().trim()
            val email = binding.finalistEmail.editText?.text.toString().trim()
            val phoneNumber = binding.finalistPhoneNumber.editText?.text.toString().trim()
            val password = binding.finalistPassword.editText?.text.toString().trim()
            val confirm_pass = binding.finalistConfirmPassword.editText?.text.toString().trim()

            if (TextUtils.isEmpty(full_name)){
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(email)){
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(phoneNumber)){
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(password)){
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(confirm_pass)){
                return@setOnClickListener
            }
            else if (!(password == confirm_pass)){
                Toast.makeText(requireContext(), "Password mismatch", Toast.LENGTH_SHORT).show()
            }
            else
                binding.progressbar.isVisible = true


            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    binding.progressbar.isVisible = false
                    Toast.makeText(requireContext(), "user created succesfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    binding.progressbar.isVisible = false
                    Toast.makeText(requireContext(), "Failed to register", Toast.LENGTH_SHORT).show()
                }
            }
        }



        return view
    }


}