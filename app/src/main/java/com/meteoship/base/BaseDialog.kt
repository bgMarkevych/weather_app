package com.meteoship.base

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.meteoship.utils.DialogClickListener

abstract class BaseDialog(val dialogId: Int) : BottomSheetDialogFragment() {

    protected lateinit var clickListener: DialogClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickListener = activity as DialogClickListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return customizeDialog(Dialog(context!!))
    }

    abstract fun customizeDialog(dialog: Dialog): Dialog

}