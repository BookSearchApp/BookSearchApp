package com.hanmo.booksearchapp.ui.search

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
        bookSearchView?.initBookList()
    }

    override fun loadBookList(bookName: String, page: Int) {
        bookSearchView?.showProgress()
        bookSearchRepository.getBookList(bookName, page)
                .doOnError { bookSearchView?.showError("Network Error") }
                .doOnSuccess { bookSearchView?.hideProgress() }
                .subscribe(
                        { res ->
                            if (res.isSuccessful) {
                                res.body()?.bookList?.let { bookList ->
                                    if (!bookList.isEmpty()) {
                                        if (page == 1) {
                                            bookSearchView?.showBookList(bookList)
                                        } else {
                                            bookSearchView?.updateBookList(bookList)
                                        }
                                    } else {
                                        bookSearchView?.showNotResult()
                                    }
                                } ?: kotlin.run {
                                    bookSearchView?.showNotResult()
                                }
                            }
                        },
                        { bookSearchView?.hideProgress() }
                ).apply { compositeDisposable.add(this) }

    }

    override fun dropView() {
        bookSearchView = null
        compositeDisposable.clear()
    }
}