package com.example.tabulizando

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.compose.foundation.layout.Arrangement

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "books.db"
        private const val DATABASE_VERSION = 2
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

    /*fun findByIsbn(isbn: Int): Book{
        val db = this.readableDatabase
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
    }*/

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

}