package com.hanmo.booksearchapp.di.module

import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.hanmo.booksearchapp.ui.search.BookSearchApiModule
import com.hanmo.booksearchapp.ui.search.BookSearchContract
import com.hanmo.booksearchapp.ui.search.BookSearchPresenter
import dagger.Binds
import dagger.Module

@Module(includes = [BookSearchApiModule::class])
abstract class BookSearchModule {

    @ActivityScoped
    @Binds
    abstract fun bookSearchPresenter(bookSearchPresenter: BookSearchPresenter) : BookSearchContract.Presenter
}