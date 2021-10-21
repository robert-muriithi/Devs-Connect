package com.example.recruiter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recruiter.R


class FinalistUpdateProfile : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finalist_update_profile, container, false)
        /*fun onCheckboxClicked(view: View) {
            if (view is CheckBox) {
                val checked: Boolean = view.isChecked

                when (view.id) {
                    R.id.checkbox_meat -> {
                        if (checked) {
                            // Put some meat on the sandwich
                        } else {
                            // Remove the meat
                        }
                    }
                    R.id.checkbox_cheese -> {
                        if (checked) {
                            // Cheese me
                        } else {
                            // I'm lactose intolerant
                        }
                    }
                    //
                }
            }
        }*/

    }


}