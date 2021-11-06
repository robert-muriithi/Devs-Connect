package com.example.recruiter.model

import android.os.Parcel
import android.os.Parcelable

data class RegisteredFinalist(
    val userID : String?= "",
    val imageUrl: String? = "",
    val name: String? = "",
    val speciality: String? = "",
    val about: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userID)
        parcel.writeString(imageUrl)
        parcel.writeString(name)
        parcel.writeString(speciality)
        parcel.writeString(about)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegisteredFinalist> {
        override fun createFromParcel(parcel: Parcel): RegisteredFinalist {
            return RegisteredFinalist(parcel)
        }

        override fun newArray(size: Int): Array<RegisteredFinalist?> {
            return arrayOfNulls(size)
        }
    }
}
