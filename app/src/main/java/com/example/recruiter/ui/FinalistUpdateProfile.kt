package com.example.recruiter.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentFinalistUpdateProfileBinding
import com.example.recruiter.model.EmployerProfile
import com.example.recruiter.model.FinalistProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.HashMap


class FinalistUpdateProfile : Fragment() {
private lateinit var binding: FragmentFinalistUpdateProfileBinding
private lateinit var databaseReference: DatabaseReference
private lateinit var firebaseDatabase: FirebaseDatabase
private lateinit var firebaseStorage: FirebaseStorage
private lateinit var firebaseAuth: FirebaseAuth

private var selectedImage: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistUpdateProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        databaseReference = FirebaseDatabase.getInstance().getReference("Profile Details")
        binding.updateImage.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 45)
        }

        binding.updateBtn.setOnClickListener {
            if (selectedImage != null){
                val reference = firebaseStorage.reference.child("Profiles").child(firebaseAuth.uid!!)
                reference.putFile(selectedImage!!).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        reference.downloadUrl.addOnSuccessListener { uri ->
                            val imageUrl = uri.toString()
                            val name: String = binding.updateName.text.toString()
                            val loc: String = binding.updateLocation.text.toString()
                            val link: String = binding.updateWorkExperience.text.toString()
                            val email: String = binding.updateEmail.text.toString()
                            val abt: String = binding.updateAbout.text.toString()
                            val skills: String = binding.skillsEditText.text.toString()
                            val uid = firebaseAuth.uid
                            val profile = FinalistProfile(imageUrl,name, loc, link, email, abt,skills)
                            firebaseDatabase.reference.child("Developers Profile Details").child(uid!!)
                                .setValue(profile).addOnSuccessListener {
                                    Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
                                    findNavController().navigate(R.id.action_empUpdateProfile_to_employerProfileFragment)
                                }.addOnFailureListener {
                                    findNavController().navigate(R.id.action_empUpdateProfile_to_employerProfileFragment)
                                    Toast.makeText(requireContext(), "Failed to update", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                    else{
                        val companyName: String = binding.updateName.text.toString()
                        val companyDesc: String = binding.updateLocation.text.toString()
                        val websiteLink: String = binding.updateWorkExperience.text.toString()
                        val companyEmail: String = binding.updateEmail.text.toString()
                        val location: String = binding.updateAbout.text.toString()
                        val about: String = binding.skillsEditText.text.toString()
                        val uid = firebaseAuth.uid
                        val employerProfile = FinalistProfile("No image", companyName, companyDesc, websiteLink, companyEmail, location, about)
                        firebaseDatabase.reference.child("Developers Profile Details").child(uid!!).setValue(employerProfile).addOnSuccessListener {

                            Toast.makeText(requireContext(), "Updated with no image", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }

        return view

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (data.data != null) {
                val uri = data.data // filepath
                val storage = FirebaseStorage.getInstance()
                val time = Date().time
                val reference = storage.reference.child("Profiles").child(time.toString() + "")
                reference.putFile(uri!!).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        reference.downloadUrl.addOnSuccessListener { uri ->
                            val filePath = uri.toString()
                            val obj = HashMap<String, Any>()
                            obj["image"] = filePath
                            firebaseDatabase.reference.child("Profile Details")
                                .child(FirebaseAuth.getInstance().uid!!)
                                .updateChildren(obj).addOnSuccessListener { }
                        }
                    }
                }
                binding.updateImage.setImageURI(data.data)
                selectedImage = data.data
            }
        }
    }
    }
