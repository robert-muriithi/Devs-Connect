package com.example.recruiter.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentEmployerCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth


class EmployerCreateAccount : Fragment() {
    private lateinit var binding: FragmentEmployerCreateAccountBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerCreateAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = FirebaseAuth.getInstance()

        binding.returnToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_employerCreateAccount_to_loginFragment)
        }

        binding.next.setOnClickListener {

           // findNavController().navigate(R.id.action_employerCreateAccount_to_companyInformation)
            val fullName = binding.employerFullName.editText?.text.toString().trim()
            val workEmail = binding.workPlaceEmail.editText?.text.toString().trim()
            val phoneNumber = binding.EmployerPhonenumber.editText?.text.toString().trim()
            val position = binding.position.editText?.text.toString().trim()
            val password = binding.employerPassword.editText?.text.toString().trim()
            val confirmPassword = binding.employerConfirmPassword.editText?.text.toString().trim()

            if (TextUtils.isEmpty(fullName)){
                binding.employerFullName.error = "required"
                //return@setOnClickListener
            }else if (TextUtils.isEmpty(workEmail)){
                binding.workPlaceEmail.error = "required"
                    //return@setOnClickListener
                }
            else if (TextUtils.isEmpty(phoneNumber)){
                binding.EmployerPhonenumber.error = "required"
               // return@setOnClickListener
            }
            else if (TextUtils.isEmpty(position)){

                binding.position.error = "required"
            //return@setOnClickListener
            }else if (TextUtils.isEmpty(password)){
                binding.employerPassword.error = "required"
            }
            else if (TextUtils.isEmpty(confirmPassword)){
                binding.employerConfirmPassword.error = "required"
            }
            else if (!password.equals(confirmPassword)){
                Toast.makeText(requireContext(), "Password does not match", Toast.LENGTH_SHORT).show()
            }
        }




        return view
    }

}