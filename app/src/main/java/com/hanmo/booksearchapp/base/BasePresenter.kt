package com.hanmo.booksearchapp.base

interface BasePresenter<T> {

    fun takeView(view: T)

    fun dropView()

}
