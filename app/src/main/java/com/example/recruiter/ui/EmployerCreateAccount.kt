package com.example.recruiter.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
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
import com.example.recruiter.others.CheckInternet
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
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
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        binding.returnToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_employerCreateAccount_to_loginFragment)
        }

        binding.create.setOnClickListener {

            // findNavController().navigate(R.id.action_employerCreateAccount_to_companyInformation)
            val fullName = binding.employerFullName.editText?.text.toString().trim()
            val email = binding.emailAddress.editText?.text.toString().trim()
            val phoneNumber = binding.EmployerPhonenumber.editText?.text.toString().trim()
            val position = binding.position.editText?.text.toString().trim()
            val compName = binding.companyName.editText?.text.toString().trim()
            val compAbout = binding.companyAbout.editText?.text.toString().trim()
            val password = binding.employerPassword.editText?.text.toString().trim()
            val confirmPassword = binding.employerConfirmPassword.editText?.text.toString().trim()

            when {
                TextUtils.isEmpty(fullName) -> {
                    binding.employerFullName.error = "required"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(email) -> {
                    binding.emailAddress.error = "required"
                    return@setOnClickListener
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    binding.emailAddress.error = "Invalid email format"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(phoneNumber) -> {
                    binding.EmployerPhonenumber.error = "required"
                    return@setOnClickListener
                }
                phoneNumber.length < 10 -> {
                    binding.EmployerPhonenumber.error = "required"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(position) -> {
                    binding.position.error = "required"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(compName) -> {
                    binding.companyName.error = "required"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(compAbout) -> {
                    binding.companyAbout.error = "required"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(password) -> {
                    binding.employerPassword.error = "required"
                    return@setOnClickListener
                }
                TextUtils.isEmpty(confirmPassword) -> {
                    binding.employerConfirmPassword.error = "required"
                    return@setOnClickListener
                }
                !password.equals(confirmPassword) -> {
                    Toast.makeText(requireContext(), "Password does not match", Toast.LENGTH_SHORT)
                        .show()
                }
                password.length < 8 -> {
                    binding.employerPassword.error = "Password too short"
                    return@setOnClickListener
                }
                !CheckInternet.isConnected(requireContext()) -> {
                    Snackbar.make(
                        requireContext(), view, "Connect to the internet and try again",
                        BaseTransientBottomBar.LENGTH_LONG
                    ).show()
                }
                else -> {
                    binding.progressBar2.isVisible = true
                    binding.employerFullName.isEnabled = false
                    binding.emailAddress.isEnabled = false
                    binding.EmployerPhonenumber.isEnabled = false
                    binding.position.isEnabled = false
                    binding.companyAbout.isEnabled = false
                    binding.employerPassword.isEnabled = false
                    binding.employerConfirmPassword.isEnabled = false

                }
            }
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    firebaseUser?.sendEmailVerification()?.addOnCompleteListener {
                        findNavController().navigate(R.id.action_employerCreateAccount_to_loginFragment)
                        Toast.makeText(
                            requireContext(),
                            "Verification link has been sent to you email",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    binding.progressBar2.isVisible = false
                    val employer = Employer(
                        category,
                        fullName,
                        email,
                        phoneNumber,
                        position,
                        compName,
                        compAbout,
                        firebaseUser?.uid
                    )
                    databaseReference.child(firebaseUser?.uid!!).setValue(employer)
                }
            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                binding.progressBar2.isVisible = false
            }

        }

        return view
    }


}