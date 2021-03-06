package com.hanmo.booksearchapp.ui.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.View
import com.hanmo.booksearchapp.R
import com.hanmo.booksearchapp.base.BaseActivity
import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.hanmo.booksearchapp.model.Book
import com.hanmo.booksearchapp.ui.detail.BookDetailActivity
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import kotlinx.android.synthetic.main.activity_book_search.*
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import android.view.inputmethod.EditorInfo
import android.widget.TextView



@ActivityScoped
class BookSearchActivity : BaseActivity(), BookSearchContract.View {

    @Inject
    lateinit var presenter : BookSearchPresenter

    private val bookSearchAdapter : BookSearchAdapter by lazy { BookSearchAdapter() }

    private val onItemClickListener = object : BookSearchAdapter.OnItemClickListener {
        override fun onItemClick(bookId: String?) {
            startDetailActivity(bookId)
        }
    }

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
            bookSearchAdapter.setOnItemClickListener(onItemClickListener)
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
                    searchBook()
                }
                .apply { presenter.compositeDisposable.add(this) }

    }

    override fun initKeyboard() {
        inputBookName.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchBook()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun searchBook() {
        val bookName = inputBookName.text.trim()
        if (bookName.isEmpty()) showError("검색어를 입력해주세요.")
        else presenter.loadBookList(bookName.toString(), 1)
    }

    override fun showBookList(bookList: MutableList<Book>) {
        bookSearchRefresh.visibility = View.VISIBLE
        notResultText.visibility = View.GONE
        bookSearchAdapter.loadBookList(bookList)
    }

    override fun updateBookList(bookList: MutableList<Book>) {
        bookSearchAdapter.updateBookList(bookList)
    }

    override fun startDetailActivity(bookId: String?) {
        bookId?.let { id ->
            startActivity(BookDetailActivity.newIntent(this, id))
        }
    }

    override fun showNotResult() {
        bookSearchRefresh.visibility = View.GONE
        notResultText.visibility = View.VISIBLE
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