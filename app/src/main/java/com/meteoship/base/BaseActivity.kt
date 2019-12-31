package com.meteoship.base

import androidx.appcompat.app.AppCompatActivity
import com.meteoship.app.dialog.ErrorDialog
import com.meteoship.utils.DialogClickListener

abstract class BaseActivity : AppCompatActivity(), BaseView, DialogClickListener {

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

    override fun showErrorDialog(error: String) {
        ErrorDialog.show(this, supportFragmentManager, error)
    }

    protected fun showGoToSettingsDialog(permission: String) {

    }

    override fun dialogClick(dialogId: Int, data: Any?) {

    }

}