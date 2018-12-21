package com.hanmo.booksearchapp.ui.search

import com.hanmo.booksearchapp.network.BookSearchApi
import com.hanmo.booksearchapp.repository.BookSearchRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class BookSearchApiModule {

    @Provides
    fun provideBookSearchApi(retrofit: Retrofit) : BookSearchApi {
        return retrofit.create(BookSearchApi::class.java)
    }

    @Provides
    fun provideBookSearchRepository(bookSearchApi: BookSearchApi) : BookSearchRepository {
        return BookSearchRepository(bookSearchApi)
    }

}