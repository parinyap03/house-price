package com.example.demoapp.Util

import android.app.AlertDialog
import android.app.Dialog
import androidx.fragment.app.Fragment
import com.example.demoapp.MainActivity
import com.example.demoapp.R

fun Fragment.getLoading(): Dialog { //สร้างฟังก์ชั่นเสริม ทุกๆfragmentสามารถใช้ฟังก์ชันนี้ได้
    val builder = AlertDialog.Builder((activity as MainActivity))
    builder.setView(R.layout.progress)
    builder.setCancelable(false)
    val dialog = builder.create()
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    return dialog
}