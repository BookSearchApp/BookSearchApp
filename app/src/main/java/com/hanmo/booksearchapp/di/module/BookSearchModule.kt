package com.hanmo.booksearchapp.di.module

import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.hanmo.booksearchapp.ui.BookSearchContract
import com.hanmo.booksearchapp.ui.BookSearchPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class BookSearchModule {

    @ActivityScoped
    @Binds
    abstract fun bookSearchPresenter(bookSearchPresenter: BookSearchPresenter) : BookSearchContract.Presenter
}