package com.hanmo.booksearchapp.ui

import android.os.Bundle
import com.hanmo.booksearchapp.R
import com.hanmo.booksearchapp.base.BaseActivity
import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class BookSearchActivity : BaseActivity(), BookSearchContract.View {

    @Inject
    lateinit var presenter : BookSearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)

        presenter.takeView(this)

    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }
}