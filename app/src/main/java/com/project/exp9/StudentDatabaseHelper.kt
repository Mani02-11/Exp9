package com.project.exp9

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StudentDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    "StudentDatabase.db",
    null,
    1
) {

    companion object {
        private const val TABLE_NAME = "students"
        private const val COL_ID = "id"
        private const val COL_NAME = "name"
        private const val COL_USN = "usn"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NAME TEXT NOT NULL,
                $COL_USN TEXT NOT NULL
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertStudent(name: String, usn: String): Long {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(COL_NAME, name)
            put(COL_USN, usn)
        }

        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllStudents(): String {
        val db = readableDatabase
        val result = StringBuilder()

        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COL_ID, COL_NAME, COL_USN),
            null,
            null,
            null,
            null,
            "$COL_ID DESC"
        )

        cursor.use {
            if (it.count == 0) {
                return "No student records found."
            }

            while (it.moveToNext()) {
                val id = it.getInt(0)
                val name = it.getString(1)
                val usn = it.getString(2)

                result.append("ID: $id\n")
                    .append("Name: $name\n")
                    .append("USN: $usn\n\n")
            }
        }

        return result.toString()
    }

    fun deleteStudent(usn: String): Int {
        val db = writableDatabase

        return db.delete(
            TABLE_NAME,
            "$COL_USN = ?",
            arrayOf(usn)
        )
    }
}