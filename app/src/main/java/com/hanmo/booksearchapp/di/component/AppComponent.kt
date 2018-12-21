package com.hanmo.booksearchapp.di.component

import android.content.Context
import com.hanmo.booksearchapp.BookSearchApp
import com.hanmo.booksearchapp.di.module.ActivityBindingModule
import com.hanmo.booksearchapp.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBindingModule::class, NetworkModule::class])
interface AppComponent : AndroidInjector<BookSearchApp> {

    override fun inject(instance: BookSearchApp?)

    @Component.Builder
    interface Builder {
        fun build() : AppComponent

        @BindsInstance
        fun application(applicationContext: Context) : Builder
    }

}