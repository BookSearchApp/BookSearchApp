package com.hanmo.booksearchapp.di.module

import com.hanmo.booksearchapp.di.annotation.ActivityScoped
import com.hanmo.booksearchapp.ui.BookSearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [BookSearchModule::class])
    abstract fun bookSearchActivity() : BookSearchActivity

}