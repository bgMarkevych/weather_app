package com.meteoship.app.dialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.meteoship.R
import com.meteoship.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_error.*

const val ERROR_DIALOG_ID = 199
const val ERROR_TITLE_KEY = "ERROR_TITLE_KEY"
const val ERROR_MESSAGE_KEY = "ERROR_MESSAGE_KEY"

class ErrorDialog : BaseDialog(ERROR_DIALOG_ID) {

    companion object {

        fun show(
            activity: Activity,
            fragmentManager: FragmentManager,
            error: String,
            title: String? = null
        ) {
            if (activity.isFinishing) {
                return
            }
            val dialog = ErrorDialog()
            val bundle = Bundle()
            bundle.putString(ERROR_MESSAGE_KEY, error)
            bundle.putString(ERROR_TITLE_KEY, title)
            dialog.arguments = bundle
            fragmentManager.beginTransaction()
                .add(dialog, ErrorDialog::class.java.canonicalName)
                .commitAllowingStateLoss()
        }

    }

    private var titleStr: String? = null
    private var messageStr: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments == null) {
            return
        }
        titleStr = arguments!!.getString(ERROR_TITLE_KEY, null)
        messageStr = arguments!!.getString(ERROR_MESSAGE_KEY, null)
    }


    override fun customizeDialog(dialog: Dialog): Dialog {
        dialog.setContentView(R.layout.dialog_error)
        dialog.findViewById<Button>(R.id.ok).setOnClickListener { dismissAllowingStateLoss() }
        val message = dialog.findViewById<TextView>(R.id.message)
        val title = dialog.findViewById<TextView>(R.id.title)
        message.text = messageStr
        if (!titleStr.isNullOrEmpty()) {
            title.text = titleStr
        }
        return dialog
    }
}