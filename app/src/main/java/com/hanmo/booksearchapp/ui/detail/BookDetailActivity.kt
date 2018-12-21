package com.hanmo.booksearchapp.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.hanmo.booksearchapp.BOOK_ID
import com.hanmo.booksearchapp.R
import com.hanmo.booksearchapp.base.BaseActivity
import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.hanmo.booksearchapp.model.BookDetail
import kotlinx.android.synthetic.main.activity_book_detail.*
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

    override fun getBookId(): String? {
        return intent?.getStringExtra(BOOK_ID)
    }

    @SuppressLint("SetTextI18n")
    override fun showBookDetail(bookDetail: BookDetail) {
        bookDetail.run {
            Glide.with(this@BookDetailActivity).load(bookImage).thumbnail(0.1f).into(detailBookImage)
            titleText.text = "Title :\n$title"
            subTitleText.text = "SubTitle :\n$subtitle"
            priceText.text = "price : $price"
            authorsText.text = "authors :\n$authors"
        }

    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showError(error: String) {
        toast(error)
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
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

