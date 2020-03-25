package net.hafiznaufalr.mobilequestion.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import net.hafiznaufalr.mobilequestion.db.MovieContract.MovieColumns.Companion.ID
import net.hafiznaufalr.mobilequestion.db.MovieContract.MovieColumns.Companion.OVERVIEW
import net.hafiznaufalr.mobilequestion.db.MovieContract.MovieColumns.Companion.POSTER
import net.hafiznaufalr.mobilequestion.db.MovieContract.MovieColumns.Companion.RELEASEDATE
import net.hafiznaufalr.mobilequestion.db.MovieContract.MovieColumns.Companion.TITLE
import net.hafiznaufalr.mobilequestion.model.Model.Movie
import java.sql.SQLException

class MovieHelper(context: Context) {
    val DATABASE_TABLE = MovieContract().TABLE_MOVIE
    var databaseHelper = DBHelper(context)
    lateinit var database: SQLiteDatabase

    companion object {

        @Volatile private var INSTANCE: MovieHelper? = null

        fun getInstance(context: Context): MovieHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MovieHelper(context)
            }

    }


    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun getAllMovie(): ArrayList<Movie> {
        val arrayList = ArrayList<Movie>()
        val cursor = database.query(
            DATABASE_TABLE,
            null, null, null, null, null,
            null, null
        )
        cursor.moveToFirst()
        var movie: Movie
        if (cursor.count > 0) {
            do {
                movie = Movie(
                    cursor.getInt(cursor.getColumnIndexOrThrow(ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)),
                    cursor.getString(cursor.getColumnIndexOrThrow(RELEASEDATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(POSTER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(TITLE))

                )

                arrayList.add(movie)
                cursor.moveToNext()

            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun isMovieFavorited(id: String): Boolean {
        val cursor = database.query(
            DATABASE_TABLE,
            null, "$ID = '$id'", null, null, null,
            null, null
        )
        cursor.moveToFirst()
        if (cursor.count > 0) {
            return true
        }
        return false

    }

    fun insertMovie(movie: Movie): Long {
        val args = ContentValues()
        args.put(ID, movie.id)
        args.put(OVERVIEW, movie.overview)
        args.put(RELEASEDATE, movie.releaseDate)
        args.put(POSTER, movie.posterPath)
        args.put(TITLE, movie.title)


        return database.insert(DATABASE_TABLE, null, args)
    }

    fun deleteMovie(id: String): Int {
        return database.delete(MovieContract().TABLE_MOVIE, "$ID = '$id'", null)
    }

}