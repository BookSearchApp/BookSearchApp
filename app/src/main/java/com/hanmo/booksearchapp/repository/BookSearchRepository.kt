package com.hanmo.booksearchapp.repository

import com.hanmo.booksearchapp.model.BookList
import com.hanmo.booksearchapp.network.BookSearchApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class BookSearchRepository(private val bookSearchApi: BookSearchApi) {

    fun getBookList(bookName : String, page : Int) : Single<Response<BookList>> {
        return bookSearchApi.getBookList(bookName, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}