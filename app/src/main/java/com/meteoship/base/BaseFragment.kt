package com.meteoship.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), BaseView {

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorDialog(error: String) {

    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}