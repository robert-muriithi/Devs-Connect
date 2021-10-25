package com.example.recruiter.model

data class FinalistMarketPlace(

    val jobTitle: String,
    val jobRole: String,
    val jobDesc: String
){
    constructor() : this("","","")
}

