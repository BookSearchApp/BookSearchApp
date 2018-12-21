package com.hanmo.booksearchapp.ui.detail

import android.util.Log
import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.hanmo.booksearchapp.repository.BookDetailRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScoped
class BookDetailPresenter @Inject constructor(private val bookDetailRepository: BookDetailRepository) : BookDetailContract.Presenter {

    private var bookDetailView : BookDetailContract.View? = null

    private val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }

    override fun takeView(view: BookDetailContract.View) {
        bookDetailView = view
        loadBookDetail()
    }

    override fun loadBookDetail() {
        bookDetailView?.showProgress()

        bookDetailView?.getBookId()?.let { bookId ->
            bookDetailRepository.getBookDetail(bookId)
                    .doOnError { bookDetailView?.hideProgress() }
                    .doOnSuccess { bookDetailView?.hideProgress() }
                    .subscribe(
                            { res ->
                                if (res.isSuccessful) {
                                    res.body()?.let { bookDetail ->
                                        bookDetailView?.showBookDetail(bookDetail)
                                    }
                                }
                            },
                            { bookDetailView?.showError("ERROR!") }
                    ).apply { compositeDisposable.add(this) }
        } ?: kotlin.run {
            bookDetailView?.hideProgress()
        }
    }

    override fun dropView() {
        bookDetailView = null
        compositeDisposable.clear()
    }

}