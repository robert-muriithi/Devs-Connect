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
import com.example.recruiter.databinding.FragmentLoginBinding
import com.example.recruiter.others.CustomDialogFragment
import com.example.recruiter.others.CustomLoading
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private  lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root



        /*val navigationView : BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationView)
        navigationView?.isVisible = false*/


        auth = FirebaseAuth.getInstance()
       binding.createAccount.setOnClickListener {
           findNavController().navigate(R.id.action_loginFragment_to_signUpCategory)
       }

        binding.forgotPassword.setOnClickListener {
            ResetPassword().show(requireActivity().supportFragmentManager,"CustomDialog")
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
                    val firebaseUser = auth.currentUser
                    if(firebaseUser!!.isEmailVerified){
                        databaseReference = FirebaseDatabase.getInstance().getReference("users")
                        val userType = binding.usertypeSpinner.selectedItem.toString()
                        //  if (databaseReference.child())
                        if (userType == "Employer"){
                            binding.progressBar.isVisible = false
                            //Toast.makeText(requireContext(), "Succesfully logged in", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_loginFragment_to_employerHomeFragment)
                        }
                        if (userType == "Developer"){
                            binding.progressBar.isVisible = false
                            findNavController().navigate(R.id.action_loginFragment_to_finalistHomeFragment)
                        }
                    }else
                    {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), "Verify email first", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    binding.progressBar.isVisible = false
                    Toast.makeText(requireContext(), "Check credentials and try again", Toast.LENGTH_SHORT).show()
                }
            }
        }






        return view
    }


}