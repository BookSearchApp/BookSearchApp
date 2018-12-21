package com.hanmo.booksearchapp.di.component

import android.content.Context
import com.hanmo.booksearchapp.BookSearchApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [])
interface AppComponent : AndroidInjector<BookSearchApp> {

    override fun inject(instance: BookSearchApp?)

    @Component.Builder
    interface Builder {
        fun build() : AppComponent

        @BindsInstance
        fun application(applicationContext: Context) : Builder
    }

}