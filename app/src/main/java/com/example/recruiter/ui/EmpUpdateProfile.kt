package com.example.recruiter.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentEmpUpdateProfileBinding
import com.example.recruiter.model.EmployerProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.HashMap


class EmpUpdateProfile : Fragment() {
    private lateinit var binding: FragmentEmpUpdateProfileBinding
    //private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseStorage: FirebaseStorage
    private var selectedImage: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEmpUpdateProfileBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        binding.empUpdateImage.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 45)
        }
        binding.updateProfileButton.setOnClickListener {
            if (selectedImage != null){
                binding.progressBar5.isVisible = true
                val reference = firebaseStorage.reference.child("Profiles").child(firebaseAuth.uid!!)
                reference.putFile(selectedImage!!).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        reference.downloadUrl.addOnSuccessListener { uri ->
                            val imageUrl = uri.toString()
                            val companyName: String = binding.companyName.text.toString()
                            val companyDesc: String = binding.companyDescription.text.toString()
                            val websiteLink: String = binding.websiteLink.text.toString()
                            val companyEmail: String = binding.companyEmail.text.toString()
                            val location: String = binding.location.text.toString()
                            val about: String = binding.companyAbout.text.toString()
                            val uid = firebaseAuth.uid
                            val employerProfile = EmployerProfile(
                                imageUrl,
                                companyName,
                                companyDesc,
                                websiteLink,
                                companyEmail,
                                location,
                                about
                            )
                            firebaseDatabase.reference.child("Company Profile Details").child(uid!!)
                                .setValue(employerProfile).addOnSuccessListener {
                                    binding.progressBar5.isVisible = false
                                    Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
                                }.addOnFailureListener {
                                    binding.progressBar5.isVisible = false
                                    Toast.makeText(requireContext(), "Failed to update", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                    else{
                        val companyName: String = binding.companyName.text.toString()
                        val companyDesc: String = binding.companyDescription.text.toString()
                        val websiteLink: String = binding.websiteLink.text.toString()
                        val companyEmail: String = binding.companyEmail.text.toString()
                        val location: String = binding.location.text.toString()
                        val about: String = binding.companyAbout.text.toString()
                        val uid = firebaseAuth.uid
                        val employerProfile = EmployerProfile("No image", companyName,companyDesc, websiteLink, companyEmail, location, about)
                        firebaseDatabase.reference.child("Profile Details").child(uid!!).setValue(employerProfile).addOnSuccessListener {
                            binding.progressBar5.isVisible = false
                            Toast.makeText(requireContext(), "Updated with no image", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            binding.progressBar5.isVisible = false
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
                binding.empUpdateImage.setImageURI(data.data)
                selectedImage = data.data
            }
        }
    }
}