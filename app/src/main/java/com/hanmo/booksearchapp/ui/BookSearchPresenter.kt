package com.hanmo.booksearchapp.ui

import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScoped
class BookSearchPresenter @Inject constructor() : BookSearchContract.Presenter {

    private var bookSearchView : BookSearchContract.View? = null

    val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }

    override fun takeView(view: BookSearchContract.View) {
        bookSearchView = view
        bookSearchView?.initSearchButton()
    }

    override fun searchBookList(bookName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dropView() {
        bookSearchView = null
        compositeDisposable.clear()
    }
}