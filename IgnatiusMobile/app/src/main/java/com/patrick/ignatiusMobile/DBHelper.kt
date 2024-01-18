package com.patrick.ignatiusMobile

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, "Quizdata.db", null, 1) {
    override fun onCreate(DB: SQLiteDatabase) {
        DB.execSQL("create Table Questions (id INTEGER PRIMARY KEY AUTOINCREMENT,question TEXT , subject TEXT,option1 TEXT,option2 TEXT,option3 TEXT,answer TEXT)")
    }

    override fun onUpgrade(DB: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // open DB by making query (on upgrade will run if onCreate does not)
        var DB = DB
        DB = writableDatabase
        DB.execSQL("select * from questions")
    }

    fun openDatabase(DB: SQLiteDatabase?) {
        var DB = DB
        DB = writableDatabase
    }

    // close DB when app is closed
    fun closeDatabase(DB: SQLiteDatabase?) {
        if (DB != null && DB.isOpen) {
            DB.close()
        }
    }

    fun insertquizdata(question: String?, subject: String?, option1: String?, option2: String?, option3: String?, answer: String?): Boolean {
        val DB = this.writableDatabase
        val contentValues = ContentValues()

        //cursor to check database
        contentValues.put("question", question)
        contentValues.put("subject", subject)
        contentValues.put("option1", option1)
        contentValues.put("option2", option2)
        contentValues.put("option3", option3)
        contentValues.put("answer", answer)
        val result = DB.insert("Questions", null, contentValues)
        return if (result == -1L) {
            false
        } else {
            true
        }
    }

    fun deleteQuizData(id: String): Boolean {
        val db = this.writableDatabase
        val whereArgs = arrayOf(id)
        val result = db.delete("Questions", "id=?", whereArgs)
        return result > 0
    }

    // get all data method
    fun getdata(): Cursor {
        val DB = this.writableDatabase
        return DB.rawQuery("Select * From Questions ", null)
    }

    val topics: Cursor
        // get subject data method
        get() {
            val DB = this.writableDatabase
            return DB.rawQuery("SELECT DISTINCT subject FROM Questions ORDER BY subject ASC", null)
        }

    // get data for quiz
    fun getQuizData(selectedSubject: String): Cursor {
        val db = this.writableDatabase
        val query = "SELECT * FROM Questions WHERE subject = ?"
        val selectionArgs = arrayOf(selectedSubject)
        return db.rawQuery(query, selectionArgs)
    }
}