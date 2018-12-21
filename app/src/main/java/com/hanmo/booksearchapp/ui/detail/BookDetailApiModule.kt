package com.hanmo.booksearchapp.ui.detail

import com.hanmo.booksearchapp.network.BookDetailApi
import com.hanmo.booksearchapp.repository.BookDetailRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class BookDetailApiModule {

    @Provides
    fun provideBookDetailApi(retrofit: Retrofit) : BookDetailApi {
        return retrofit.create(BookDetailApi::class.java)
    }

    @Provides
    fun provideBookDetailRepository(bookDetailApi: BookDetailApi) : BookDetailRepository {
        return BookDetailRepository(bookDetailApi)
    }

}