package com.meteoship.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T: BaseView> {

    protected var view: T? = null
    protected val compositeDisposable = CompositeDisposable()

    fun attach(view: T){
        this.view = view
    }

    fun detach(){
        this.view = null
        compositeDisposable.dispose()
    }

    protected fun processError(throwable: Throwable){
        throwable.printStackTrace()
        view?.showErrorDialog(throwable.localizedMessage)
    }

}