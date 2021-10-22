package com.example.recruiter.ui

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
import com.example.recruiter.databinding.FragmentEmployerCreateAccountBinding
import com.example.recruiter.model.Employer
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class EmployerCreateAccount : Fragment() {
    private lateinit var binding: FragmentEmployerCreateAccountBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    val category = "Employer"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmployerCreateAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Registered Users")

        binding.returnToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_employerCreateAccount_to_loginFragment)
        }

        binding.create.setOnClickListener {

           // findNavController().navigate(R.id.action_employerCreateAccount_to_companyInformation)
            val fullName = binding.employerFullName.editText?.text.toString().trim()
            val email = binding.emailAddress.editText?.text.toString().trim()
            val phoneNumber = binding.EmployerPhonenumber.editText?.text.toString().trim()
            val position = binding.position.editText?.text.toString().trim()
            val password = binding.employerPassword.editText?.text.toString().trim()
            val confirmPassword = binding.employerConfirmPassword.editText?.text.toString().trim()

            if (TextUtils.isEmpty(fullName)){
                binding.employerFullName.error = "required"
                //return@setOnClickListener
            }else if (TextUtils.isEmpty(email)){
                binding.emailAddress.error = "required"
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
            }else
                binding.progressBar2.isVisible = true
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful){
                    binding.progressBar2.isVisible = false
                    val employer = Employer(category,fullName, email, phoneNumber, position)
                    databaseReference.push().setValue(employer)


                }
            }

        }

        return view
    }


}