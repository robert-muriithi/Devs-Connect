package com.example.recruiter.others

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import com.example.recruiter.R

class CustomDialog {

    fun showResetPasswordDialog(activity: Activity?){
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_reset_success)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val close = dialog.findViewById<Button>(R.id.close_btn)
        close.setOnClickListener {
            dialog.dismiss()
            activity!!.finish()

        }
        dialog.show()

    }
}