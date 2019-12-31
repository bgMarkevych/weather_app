package com.meteoship.base

interface BaseView {

    fun showLoading()

    fun dismissLoading()

    fun showErrorDialog(error: String)

}