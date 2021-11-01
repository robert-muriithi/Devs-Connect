package com.example.recruiter.model

import android.os.Parcel
import android.os.Parcelable

data class RegisteredFinalist(
    val userID : String?= "",
    val imageUrl: String? = "",
    val name: String? = "",
    val speciality: String? = "",
    val about: String? = ""
)
