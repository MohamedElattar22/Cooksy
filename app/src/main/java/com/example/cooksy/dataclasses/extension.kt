package com.example.cooksy.dataclasses

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder


fun Fragment.showMessage(
    message: String,
    posMessage: String,
    posAction: DialogInterface.OnClickListener,
    negMessage: String,
    negAction: DialogInterface.OnClickListener
): AlertDialog {
    val alert = AlertDialog.Builder(context)
    alert.setMessage(message)
    alert.setPositiveButton(posMessage, posAction)
    alert.setNegativeButton(negMessage, negAction)
    return alert.show()
}

fun Activity.showMessage(
    title : String ,
    message: String,
    posMessage: String,
    posAction: DialogInterface.OnClickListener,
): androidx.appcompat.app.AlertDialog? {
    val alert = MaterialAlertDialogBuilder(this)
    alert.setTitle(title)
    alert.setMessage(message)
    alert.setPositiveButton(posMessage, posAction)
    return alert.show()
}
//    MaterialAlertDialogBuilder(context)
//        .setTitle("Something went wrong")
//        .setNegativeButton("try Again " ,object : DialogInterface.OnClickListener {
//            override fun onClick(p0: DialogInterface?, p1: Int) {
//                reload()
//            }
//        }
//        ).show()
//}