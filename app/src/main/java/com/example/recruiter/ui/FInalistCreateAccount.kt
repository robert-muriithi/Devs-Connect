package com.example.recruiter.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentFinalistCreateAccountBinding
import com.example.recruiter.model.Finalist
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.util.Patterns




class SignInFragment : Fragment() {

    private lateinit var binding: FragmentFinalistCreateAccountBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    val category = "Developer"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistCreateAccountBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        binding.return2Login.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_loginFragment)

        }

        binding.registerBtn.setOnClickListener {
            val specialty = binding.spinner.selectedItem.toString().trim()
            val full_name = binding.finalistFullName.editText?.text.toString().trim()
            val email = binding.finalistEmail.editText?.text.toString().trim()
            val phoneNumber = binding.finalistPhoneNumber.editText?.text.toString().trim()
            val password = binding.finalistPassword.editText?.text.toString().trim()
            val confirm_pass = binding.finalistConfirmPassword.editText?.text.toString().trim()

            if (binding.spinner.count == 0){
                val errorText = binding.spinner.selectedView as TextView
                errorText.error = "client required"
                errorText.requestFocus()
                return@setOnClickListener

            }
            if (TextUtils.isEmpty(full_name)){
                binding.finalistFullName.error = "Required"
            }
            else if (TextUtils.isEmpty(email)){
                binding.finalistEmail.error = "Required"
            }
            else if (TextUtils.isEmpty(phoneNumber)){
                binding.finalistPhoneNumber.error = "Required"
            }
            else if (TextUtils.isEmpty(password)){
                binding.finalistPassword.error = "Required"
            }
            else if (TextUtils.isEmpty(confirm_pass)){
                binding.finalistConfirmPassword.error = "Required"
            }
            else if (!(password == confirm_pass)){
                Toast.makeText(requireContext(), "Password mismatch", Toast.LENGTH_SHORT).show()
            }
            else
                binding.progressbar.isVisible = true
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d(TAG,"onCreateView: Registration initialized")
                    val firebaseUser = auth.currentUser
                    firebaseUser?.sendEmailVerification()?.addOnCompleteListener {
                        Toast.makeText(requireContext(), "Verification link has been sent to you email", Toast.LENGTH_SHORT).show()
                    }
                    binding.progressbar.isVisible = false
                    val finalist = Finalist(category, full_name, email, phoneNumber,password, firebaseUser?.uid)
                    databaseReference.push().setValue(finalist)
                    Toast.makeText(requireContext(), "Account Created Succesfully", Toast.LENGTH_SHORT).show()


                }
            }/*.addOnFailureListener {
                if (it.)
                }*/
        }


        return view
    }


    private fun isValidMail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }


}