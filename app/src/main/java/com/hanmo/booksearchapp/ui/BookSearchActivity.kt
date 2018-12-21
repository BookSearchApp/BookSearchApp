package com.hanmo.booksearchapp.ui

import android.os.Bundle
import com.hanmo.booksearchapp.R
import com.hanmo.booksearchapp.base.BaseActivity
import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers
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
                .filter { inputBookName.text.trim().isEmpty() }
                .observeOn(mainThread())
                .doOnNext { showError("검색어를 입력해주세요.") }
                .filter { inputBookName.text.trim().isNotEmpty() }
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe {
                    presenter.searchBookList(inputBookName.text.trim().toString())
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