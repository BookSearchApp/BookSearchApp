package com.hanmo.booksearchapp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.hanmo.booksearchapp.BOOK_ID
import com.hanmo.booksearchapp.R
import com.hanmo.booksearchapp.base.BaseActivity
import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import org.jetbrains.anko.toast
import javax.inject.Inject

@ActivityScoped
class BookDetailActivity : BaseActivity(), BookDetailContract.View {

    @Inject
    lateinit var presenter: BookDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        presenter.takeView(this)
    }

    override fun showError(error: String) {
        toast(error)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    companion object {
        fun newIntent(context: Context, bookId : String) : Intent {

            val bookDetailIntent = Intent(context, BookDetailActivity::class.java)
            with(bookDetailIntent) {
                putExtra(BOOK_ID, bookId)
            }

            return bookDetailIntent
        }
    }
}

