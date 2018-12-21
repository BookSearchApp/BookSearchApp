package com.hanmo.booksearchapp.di.module

import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.hanmo.booksearchapp.ui.detail.BookDetailApiModule
import dagger.Binds
import dagger.Module

@Module(includes = [BookDetailApiModule::class])
abstract class BookDetailModule {

    @ActivityScoped
    @Binds
    abstract fun bookSearchPresenter(bookDetailPresenter: BookDetailPresenter) : BookDetailContract.Presenter
}