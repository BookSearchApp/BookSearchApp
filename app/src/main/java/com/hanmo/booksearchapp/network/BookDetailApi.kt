package com.hanmo.booksearchapp.network

import com.hanmo.booksearchapp.model.BookDetail
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookDetailApi {

    @GET("book/{bookId}")
    fun getBookDetail(@Path("bookId") bookId : String) : Single<Response<BookDetail>>
}