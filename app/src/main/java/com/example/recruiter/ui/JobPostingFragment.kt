package com.example.recruiter.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.recruiter.R
import com.example.recruiter.databinding.FragmentJobPostingBinding
import com.example.recruiter.model.JobsPost
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class JobPostingFragment : Fragment() {
private lateinit var binding: FragmentJobPostingBinding
private lateinit var databaseReference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJobPostingBinding.inflate(inflater,container,false)
        val view = binding.root

        databaseReference = FirebaseDatabase.getInstance().getReference("Jobs_Posted")


        binding.post.setOnClickListener {
            val jobTitle = binding.jobTitle.editText?.text.toString().trim()
            val jobRole = binding.role.editText?.text.toString().trim()
            val jobDescription = binding.jobDescription.editText?.text.toString().trim()

            if (TextUtils.isEmpty(jobTitle)){
                binding.jobTitle.error = "Required"
            }
            else if (TextUtils.isEmpty(jobRole)){
                binding.role.error = "Required"
            }
            else if (TextUtils.isEmpty(jobDescription)){
                binding.jobDescription.error = "Required"
            }
            else{
                binding.postJobProgressBar.isVisible = true
                senDataToFirebase(jobTitle,jobRole,jobDescription)
            }
        }










        return view
    }

    private fun senDataToFirebase(jobTitle: String, jobRole: String, jobDescription: String) {
        val jobsPost = JobsPost(jobTitle,jobRole,jobDescription)
        databaseReference.push().setValue(jobsPost)
        binding.postJobProgressBar.isVisible = false

    }


}