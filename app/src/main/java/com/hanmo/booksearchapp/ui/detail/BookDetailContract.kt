package com.hanmo.booksearchapp.ui.detail

import com.hanmo.booksearchapp.base.BasePresenter
import com.hanmo.booksearchapp.base.BaseView
import com.hanmo.booksearchapp.model.BookDetail

interface BookDetailContract {

    interface View : BaseView {
        fun getBookId() : String?
        fun showBookDetail(bookDetail: BookDetail)
    }

    interface Presenter : BasePresenter<View> {
        fun loadBookDetail()
    }

}