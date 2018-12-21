package com.hanmo.booksearchapp

import com.hanmo.booksearchapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BookSearchApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val applicationComponent =
                DaggerAppComponent
                        .builder()
                        .application(this)
                        .build()

        applicationComponent.inject(this)

        return applicationComponent
    }
}