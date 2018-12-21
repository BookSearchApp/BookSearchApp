package com.hanmo.booksearchapp.ui

import android.os.Bundle
import com.hanmo.booksearchapp.R
import com.hanmo.booksearchapp.base.BaseActivity
import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import kotlinx.android.synthetic.main.activity_book_search.*
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit
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

    override fun initSearchButton() {
        searchButton.clicks()
                .throttleFirst(300, TimeUnit.MILLISECONDS, mainThread())
                .subscribe {
                    val bookName = inputBookName.text.trim()
                    if (bookName.isEmpty()) showError("검색어를 입력해주세요.")
                    else presenter.searchBookList(bookName.toString())
                }
                .apply { presenter.compositeDisposable.add(this) }

    }

    override fun showBookList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNotResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        toast(error)
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }
}