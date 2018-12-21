package com.hanmo.booksearchapp.ui

import com.hanmo.booksearchapp.base.BasePresenter
import com.hanmo.booksearchapp.base.BaseView
import com.hanmo.booksearchapp.model.Book

interface BookSearchContract {

    interface View : BaseView {

        fun initSearchButton()
        fun initBookList()
        fun showBookList(bookList: MutableList<Book>)
        fun updateBookList(bookList: MutableList<Book>)
        fun showNotResult()
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BasePresenter<View> {
        fun loadBookList(bookName : String, page : Int)
    }

}