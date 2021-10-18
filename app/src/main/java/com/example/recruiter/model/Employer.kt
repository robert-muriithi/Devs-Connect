package com.example.recruiter.model

data class Employer(
    val category : String,
    val full_name: String? = "",
    val workplace_email : String? = "",
    val phone_number: String? = "",
    val position : String? = ""
)
