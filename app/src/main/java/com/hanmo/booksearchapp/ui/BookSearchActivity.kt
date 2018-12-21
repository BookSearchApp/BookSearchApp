package com.hanmo.booksearchapp.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hanmo.booksearchapp.R
import com.hanmo.booksearchapp.base.BaseActivity
import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.hanmo.booksearchapp.model.Book
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

    private val bookSearchAdapter : BookSearchAdapter by lazy { BookSearchAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)

        presenter.takeView(this)

        bookSearchRefresh.setOnRefreshListener {
            presenter.loadBookList(inputBookName.text.trim().toString(), 1)
        }

    }

    override fun initBookList() {
        bookList?.run {
            adapter = bookSearchAdapter

            val infiniteScrollListener = object : InfiniteScrollListener(layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    presenter.loadBookList(inputBookName.text.trim().toString(), page)
                }
            }

            addOnScrollListener(infiniteScrollListener)
        }
    }

    override fun initSearchButton() {
        searchButton.clicks()
                .throttleFirst(300, TimeUnit.MILLISECONDS, mainThread())
                .subscribe {
                    val bookName = inputBookName.text.trim()
                    if (bookName.isEmpty()) showError("검색어를 입력해주세요.")
                    else presenter.loadBookList(bookName.toString(), 1)
                }
                .apply { presenter.compositeDisposable.add(this) }

    }

    override fun showBookList(bookList: MutableList<Book>) {
        bookSearchAdapter.loadBookList(bookList)
    }

    override fun updateBookList(bookList: MutableList<Book>) {
        bookSearchAdapter.updateBookList(bookList)
    }

    override fun showNotResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        bookSearchRefresh.isRefreshing = true
    }

    override fun hideProgress() {
        bookSearchRefresh.isRefreshing = false
    }

    override fun showError(error: String) {
        toast(error)
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }
}