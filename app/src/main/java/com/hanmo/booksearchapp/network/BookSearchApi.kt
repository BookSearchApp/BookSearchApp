package com.hanmo.booksearchapp.network

import com.hanmo.booksearchapp.model.BookList
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookSearchApi {

    @GET("search/{bookName}")
    fun getBookList(@Path("bookName") bookName : String) : Single<Response<BookList>>
}