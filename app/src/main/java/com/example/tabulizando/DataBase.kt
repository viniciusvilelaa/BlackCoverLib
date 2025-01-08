package com.example.tabulizando

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context: Context): SQLiteOpenHelper(context, NOME, null, VERSAO) {
    companion object{
        private const val NOME = "booksdb"
        private const val VERSAO = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """
            CREATE TABLE produtos (
            isbn INTEGER PRIMARY KEY,
            title TEXT,
            author TEXT,
            publisher TEXT,
            urlImg TEXT
        )
        """
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun saveBook(isbn: Int, title:String, author: String, publisher: String, urlImg: String): Long{
        val banco = this.writableDatabase
        val container = ContentValues()
        container.put("isbn", isbn)
        container.put("title", title)
        container.put("author", author)
        container.put("publisher", publisher)
        container.put("urlImg", urlImg)
        val result = banco.insert("booksdb", null, container)
        banco.close()
        return result

    }

    fun findByIsbn(isbn: Int): Book{
        val banco = this.readableDatabase
        val cursor = banco.rawQuery("SELECT * FROM database WHERE isbn=$isbn", null)
        val book = Book()
        if(cursor.count > 0){
            cursor.moveToFirst()
            do{
                book.isbn = cursor.getInt(0)
                book.title = cursor.getString(1)
                book.author = cursor.getString(2)
                book.publisher = cursor.getString(3)
                book.imgUrl = cursor.getString(4)
            }while (cursor.moveToNext())
        }
        return book
    }

    fun listAll(): ArrayList<Book>{
        val banco = this.readableDatabase
        val cursor = banco.rawQuery("SELECT  * FROM database", null)
        val array = ArrayList<Book>()
        if( cursor.count > 0){
            cursor.moveToFirst()
            do {
                var isbn = cursor.getInt(0)
                var title = cursor.getString(1)
                var author = cursor.getString(2)
                var publisher = cursor.getString(3)
                var imgUrl = cursor.getString(4)
            }while (cursor.moveToNext())
        }
        return array
    }

}