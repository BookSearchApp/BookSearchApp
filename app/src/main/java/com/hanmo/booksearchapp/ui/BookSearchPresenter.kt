package com.hanmo.booksearchapp.ui

import android.util.Log
import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.hanmo.booksearchapp.repository.BookSearchRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScoped
class BookSearchPresenter @Inject constructor(private val bookSearchRepository: BookSearchRepository) : BookSearchContract.Presenter {

    private var bookSearchView : BookSearchContract.View? = null

    val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }

    override fun takeView(view: BookSearchContract.View) {
        bookSearchView = view
        bookSearchView?.initSearchButton()
    }

    override fun searchBookList(bookName: String) {
        bookSearchRepository.getBookList(bookName)
                .doOnError { Log.e("hanmoleeee errr", it.toString()) }
                .subscribe(
                        {
                            Log.e("hanmoleeee res", it.body()?.bookList.toString())
                        },
                        {}
                ).apply { compositeDisposable.add(this) }

    }

    override fun dropView() {
        bookSearchView = null
        compositeDisposable.clear()
    }
}