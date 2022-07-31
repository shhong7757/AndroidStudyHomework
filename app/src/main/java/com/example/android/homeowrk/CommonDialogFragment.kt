package com.example.android.homeowrk

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

private const val argsMessage = "message"

class CommonDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            message = it.getString(argsMessage)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder
                .setMessage(message)
                .setNegativeButton(R.string.alert_dialog_ok) { _, _ ->
                    // User cancelled the dialog
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        @JvmStatic
        fun newInstance(value: String) =
            FlowerListFragment().apply {
                arguments = Bundle().apply {
                    putString(argsMessage, value)
                }
            }
    }
}