package com.example.tabulizando

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.compose.foundation.layout.Arrangement

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "books.db"
        private const val DATABASE_VERSION = 3
        const val TABLE_NAME = "books"
        const val COLUMN_TITLE = "title"
        const val COLUMN_AUTHOR = "author"
        const val COLUMN_PUBLISHER = "publisher"
        const val COLUMN_ISBN = "isbn"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_IMG_URL = "imgUrl"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ISBN INTEGER PRIMARY KEY,
                $COLUMN_TITLE TEXT NOT NULL,
                $COLUMN_AUTHOR TEXT NOT NULL,
                $COLUMN_PUBLISHER TEXT NOT NULL,
                $COLUMN_DESCRIPTION TEXT NOT NULL,
                $COLUMN_IMG_URL TEXT
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun saveBook(dbHelper: DataBaseHelper, book: Book) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ISBN, book.isbn)
            put(COLUMN_TITLE, book.title)
            put(COLUMN_AUTHOR, book.author)
            put(COLUMN_PUBLISHER, book.publisher)
            put(COLUMN_DESCRIPTION, book.description)
            put(COLUMN_IMG_URL, book.imgUrl)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun deleteByIsbn(isbn: Int): Boolean{
        val db = this.writableDatabase
        val result = db.delete("books", "isbn = ?", arrayOf(isbn.toString()))
        db.close()
        return result > 0
    }

    fun listAll(dbHelper: DataBaseHelper): ArrayList<Book>{
        val db = dbHelper.writableDatabase
        val cursor = db.query(TABLE_NAME,null,null,null,null,null,null)
        val books = arrayListOf<Book>()
        with(cursor){
            while (moveToNext()){
                val title = getString(getColumnIndexOrThrow(COLUMN_TITLE))
                val author = getString(getColumnIndexOrThrow(COLUMN_AUTHOR))
                val publisher = getString(getColumnIndexOrThrow(COLUMN_PUBLISHER))
                val isbn = getInt(getColumnIndexOrThrow(COLUMN_ISBN))
                val description = getString(getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val imgUrl = getString(getColumnIndexOrThrow(COLUMN_IMG_URL))

                books.add(Book(title,author,publisher,isbn, description, imgUrl))
            }
        }
        db.close()
        return books
    }

    fun updateBook(isbn: Int, title: String, author: String, publisher: String, description: String, imgUrl: String) : Boolean{
        val db = this.writableDatabase

        val contentValues = ContentValues().apply {
            if (title.isNotBlank()){
                put("title", title)
            }
            if (author.isNotBlank()){
                put("author", title)
            }
            if (publisher.isNotBlank()){
                put("publisher", publisher)
            }
            if (description.isNotBlank()){
                put("description", description)
            }
            if (imgUrl.isNotBlank()){
                put("imgUrl", imgUrl)
            }
        }

        val result = db.update("books",
            contentValues, "isbn = ?",
            arrayOf(isbn.toString())
        )

        db.close()

        return result > 0
    }

}