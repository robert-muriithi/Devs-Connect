package com.example.recruiter.model

import android.os.Parcel
import android.os.Parcelable

data class BookmarkedContacts(
    val image: String? ="",
    val name: String?="",
    val speciality: String?="",
    val userID: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(speciality)
        parcel.writeString(userID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookmarkedContacts> {
        override fun createFromParcel(parcel: Parcel): BookmarkedContacts {
            return BookmarkedContacts(parcel)
        }

        override fun newArray(size: Int): Array<BookmarkedContacts?> {
            return arrayOfNulls(size)
        }
    }
}