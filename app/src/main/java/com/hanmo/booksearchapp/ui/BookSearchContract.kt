package com.hanmo.booksearchapp.ui

import com.hanmo.booksearchapp.base.BasePresenter
import com.hanmo.booksearchapp.base.BaseView

interface BookSearchContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }

}