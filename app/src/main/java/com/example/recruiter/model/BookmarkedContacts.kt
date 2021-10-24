package com.example.recruiter.model

data class BookmarkedContacts(
    val image: String,
    val name: String,
    val speciality: String
){
    constructor(): this("","","")
}
