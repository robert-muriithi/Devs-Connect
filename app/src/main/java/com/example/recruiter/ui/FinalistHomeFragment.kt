package com.example.recruiter.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentFinalistHomeBinding

class FinalistHomeFragment : Fragment() {
private lateinit var binding: FragmentFinalistHomeBinding
    private val PICK_IMAGE = 1
    var imageUri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalistHomeBinding.inflate(layoutInflater,container, false)
        val view = binding.root

        binding.profileImage.setOnClickListener {
            openGallery()
        }


        return view
    }

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery,PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_OK && requestCode == PICK_IMAGE && data !== null && data.data != null){
            imageUri = data.data
            binding.profileImage.setImageURI(imageUri)
        }
    }

}