package com.hanmo.booksearchapp.ui.search

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hanmo.booksearchapp.R
import com.hanmo.booksearchapp.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BookSearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var bookList : MutableList<Book> = mutableListOf()

    private lateinit var itemClickListener : OnItemClickListener

    fun setOnItemClickListener(itemClickListener : OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(bookId: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookListHolder(parent)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is BookListHolder -> holder.onBind(bookList[position])
        }
    }

    fun loadBookList(bookList : MutableList<Book>) {
        this.bookList = bookList
        notifyDataSetChanged()
    }

    fun updateBookList(bookList : MutableList<Book>) {
        this.bookList.addAll(bookList)
        notifyDataSetChanged()
    }

    inner class BookListHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)) , View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        fun onBind(book: Book) {
            itemView?.run {
                Glide.with(context).load(book.bookImage).thumbnail(0.1f).into(bookImage)
                bookNameText.text = book.title
            }
        }

        override fun onClick(v: View?) {
            itemClickListener.onItemClick(bookList[adapterPosition].id)
        }
    }
}