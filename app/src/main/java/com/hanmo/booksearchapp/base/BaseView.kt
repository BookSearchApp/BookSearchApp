package com.hanmo.booksearchapp.base

interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showError(error : String)
}