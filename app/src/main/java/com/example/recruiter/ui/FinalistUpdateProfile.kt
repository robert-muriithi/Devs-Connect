package com.example.recruiter.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentFinalistUpdateProfileBinding
import com.example.recruiter.model.FinalistProfile
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class FinalistUpdateProfile : Fragment() {
private lateinit var binding: FragmentFinalistUpdateProfileBinding
private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistUpdateProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        databaseReference = FirebaseDatabase.getInstance().getReference("Profile Details")

        binding.updateBtn.setOnClickListener {
            val name = binding.updateName.text.toString()
            val location = binding.updateLocation.text.toString()
            val workExperience = binding.updateWorkExperience.text.toString()
            val email = binding.updateEmail.text.toString()
            val about = binding.updateAbout.text.toString()
         //   val javaCheckBox = binding.javaCheckbox.isChecked

            when {
                TextUtils.isEmpty(name) -> {
                    binding.updateName.error = "Required"
                }
                TextUtils.isEmpty(location) -> {
                    binding.updateLocation.error = "Required"
                }
                TextUtils.isEmpty(workExperience) -> {
                    binding.updateWorkExperience.error = "Required"
                }
                TextUtils.isEmpty(email) -> {
                    binding.updateEmail.error= "Required"
                }
                TextUtils.isEmpty(about) -> {
                    binding.updateAbout.error = "Required"
                }
                else -> {
                    binding.progressBar4.isVisible = true
                    sendDataToFirebase("fnlfnkl",location, workExperience, email,about,"dbsdkflhdkl","nfnfklnklf")

                }
            }
        }

        return view

    }

    private fun sendDataToFirebase(imageUrl: String,
                                   name: String, location: String,
                                   workExperience: String, email: String,
                                   coverLetter: String, about: String ) {

        val finalistProfile = FinalistProfile(imageUrl, name, location, workExperience, email, coverLetter, about, "Android developer")
        databaseReference.push().setValue(finalistProfile).addOnSuccessListener {
            binding.progressBar4.isVisible = false
            Toast.makeText(requireContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Failed to update", Toast.LENGTH_SHORT).show()
            binding.progressBar4.isVisible = false
        }


    }
    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.javaCheckbox -> {
                    if (checked) {
                        databaseReference.push().setValue(view)
                    }
                }
                R.id.pythonCheckbox -> {
                    if (checked) {
                        databaseReference.push().setValue(view)
                    }
                }
                R.id.javascriptCheckbox ->{
                    if (checked){
                        databaseReference.push().setValue(view)
                    }
                }
                R.id.cppCheckbox -> {
                    if (checked){
                        databaseReference.push().setValue(view)
                    }
                }
                R.id.htmlCheckbox -> {
                    if (checked){
                        databaseReference.push().setValue(view)
                    }
                }
                R.id.uxCheckbox -> {
                    if (checked){
                        databaseReference.push().setValue(view)
                    }
                }
                //
            }
        }
    }



}