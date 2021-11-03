package com.example.recruiter.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    private var category1 = "Employer"
    private val category2 = "Developer"
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
            ResetPassword().show(requireActivity().supportFragmentManager, "CustomDialog")
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.loginEmail.editText?.text.toString().trim()
            val password = binding.loginPassword.editText?.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                return@setOnClickListener
            } else if (TextUtils.isEmpty(password)) {
                return@setOnClickListener
            } else {
                binding.progressBar.isVisible = true
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val firebaseUser = auth.currentUser
                    if (firebaseUser!!.isEmailVerified) {

                        databaseReference = FirebaseDatabase.getInstance().getReference("users")
                        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
                        val uid: String? = user?.uid
                        Toast.makeText(requireContext(), "$uid", Toast.LENGTH_SHORT).show()

                        databaseReference.child(uid!!)
                            .addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    if (snapshot.exists()) {
                                        for (i in snapshot.children) {
                                            if (i.key.equals("category")) {
                                                val type: String = i.value.toString()
                                                // Toast.makeText(requireContext(), "$type", Toast.LENGTH_SHORT).show()
                                                if (type == "Employer") {
                                                    findNavController().navigate(R.id.action_loginFragment_to_employerHomeFragment)
                                                } else {
                                                    findNavController().navigate(R.id.action_loginFragment_to_finalistHomeFragment)
                                                }
                                            }
                                        }
                                        /*Toast.makeText(requireContext(), snapshot.value.toString(), Toast.LENGTH_SHORT).show()
                                        val type: String = snapshot.value.toString()
                                        Log.d(TAG, "onDataChange: $type")*/
                                        //snapshot.child(uid!!).child("category").value.toString()

                                    } else {
                                        Toast.makeText(
                                            requireContext(),
                                            "Does not exist",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    Toast.makeText(
                                        requireContext(),
                                        error.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }


                            })

                    } else {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            requireContext(),
                            "Check credentials and try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }


        }

        return view

    }
}