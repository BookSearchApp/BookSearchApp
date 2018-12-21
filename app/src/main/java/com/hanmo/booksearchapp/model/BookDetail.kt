package com.hanmo.booksearchapp.model

import com.google.gson.annotations.SerializedName

data class BookDetail(
        @SerializedName("title") var title : String?,
        @SerializedName("subtitle") var subtitle : String?,
        @SerializedName("price") var price : String?,
        @SerializedName("authors") var authors : String?,
        @SerializedName("image") var bookImage : String?

)