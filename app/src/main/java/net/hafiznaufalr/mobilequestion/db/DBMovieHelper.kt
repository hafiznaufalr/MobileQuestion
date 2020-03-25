package net.hafiznaufalr.mobilequestion.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        var DATABASE_NAME = "favorite"
        private val DATABASE_VERSION = 1
    }

    private val SQL_CREATE_TABLE_MOVIE = String.format(
        "CREATE TABLE %s"
                + " (%s TEXT PRIMARY KEY NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL)",
        MovieContract().TABLE_MOVIE,
        MovieContract.MovieColumns.ID,
        MovieContract.MovieColumns.OVERVIEW,
        MovieContract.MovieColumns.RELEASEDATE,
        MovieContract.MovieColumns.POSTER,
        MovieContract.MovieColumns.TITLE

    )

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_MOVIE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + MovieContract().TABLE_MOVIE)
    }
}