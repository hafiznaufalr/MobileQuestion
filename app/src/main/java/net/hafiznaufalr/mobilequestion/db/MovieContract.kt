package net.hafiznaufalr.mobilequestion.db

import android.net.Uri
import android.provider.BaseColumns

class MovieContract {
    var TABLE_MOVIE = "movie"
    internal class MovieColumns : BaseColumns {
        companion object {
            var ID = "id"
            var OVERVIEW = "overview"
            var POSTER = "posterPath"
            var RELEASEDATE = "releaseDate"
            var TITLE = "title"
        }
    }
}
