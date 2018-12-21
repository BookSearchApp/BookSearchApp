package com.hanmo.booksearchapp.ui

import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BookSearchPresenter @Inject constructor() : BookSearchContract.Presenter {

    private var bookSearchView : BookSearchContract.View? = null

    override fun takeView(view: BookSearchContract.View) {
        bookSearchView = view
    }

    override fun dropView() {
        bookSearchView = null
    }
}