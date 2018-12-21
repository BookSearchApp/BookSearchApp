package com.hanmo.booksearchapp.repository

import com.hanmo.booksearchapp.model.BookDetail
import com.hanmo.booksearchapp.network.BookDetailApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class BookDetailRepository(private val bookDetailApi: BookDetailApi) {

    fun getBookDetail(bookId : String) : Single<Response<BookDetail>> {
        return bookDetailApi.getBookDetail(bookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}