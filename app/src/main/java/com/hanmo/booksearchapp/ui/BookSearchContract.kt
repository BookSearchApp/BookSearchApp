package com.hanmo.booksearchapp.ui

import com.hanmo.booksearchapp.base.BasePresenter
import com.hanmo.booksearchapp.base.BaseView

interface BookSearchContract {

    interface View : BaseView {

        fun initSearchButton()
        fun showBookList()
        fun showNotResult()
    }

    interface Presenter : BasePresenter<View> {
        fun searchBookList(bookName : String)
    }

}