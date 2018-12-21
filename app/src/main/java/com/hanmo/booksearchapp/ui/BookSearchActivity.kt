package com.hanmo.booksearchapp.ui

import android.os.Bundle
import com.hanmo.booksearchapp.R
import com.hanmo.booksearchapp.base.BaseActivity

class BookSearchActivity : BaseActivity(), BookSearchContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)
        
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}