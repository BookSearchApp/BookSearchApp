package com.hanmo.booksearchapp.ui.detail

import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.hanmo.booksearchapp.repository.BookDetailRepository
import javax.inject.Inject

@ActivityScoped
class BookDetailPresenter @Inject constructor(private val bookDetailRepository: BookDetailRepository) : BookDetailContract.Presenter {

    private var bookDetailView : BookDetailContract.View? = null

    override fun takeView(view: BookDetailContract.View) {
        bookDetailView = view
    }

    override fun dropView() {
        bookDetailView = null
    }

}