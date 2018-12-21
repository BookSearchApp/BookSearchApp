package com.hanmo.booksearchapp.ui

class BookSearchPresenter : BookSearchContract.Presenter {

    private var bookSearchView : BookSearchContract.View? = null

    override fun takeView(view: BookSearchContract.View) {
        bookSearchView = view
    }

    override fun dropView() {
        bookSearchView = null
    }
}