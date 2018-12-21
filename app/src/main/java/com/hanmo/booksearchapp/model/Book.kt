package com.hanmo.booksearchapp.model

import com.google.gson.annotations.SerializedName

data class BookList(
        @SerializedName("books") var bookList: MutableList<Book>
)
data class Book(
        @SerializedName("isbn13") var id : String?,
        @SerializedName("title") var title : String?,
        @SerializedName("image") var bookImage : String?
)