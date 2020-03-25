package net.hafiznaufalr.mobilequestion.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_favorite.*
import net.hafiznaufalr.mobilequestion.R
import net.hafiznaufalr.mobilequestion.db.MovieHelper
import net.hafiznaufalr.mobilequestion.model.Model.Movie

class FavoriteActivity : AppCompatActivity() {
    lateinit var movieHelper: MovieHelper
    lateinit var adapter: FavoriteAdapter
    private var listFavorite: ArrayList<Movie> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.fav_movie)
        movieHelper = MovieHelper.getInstance(this)
        movieHelper.open()
        prepareRv()
    }

    private fun prepareRv() {
        listFavorite = movieHelper.getAllMovie()
        adapter = FavoriteAdapter(this, listFavorite)
        rv_favorite.adapter = adapter
        if (listFavorite.isEmpty()){
            tv_null.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        listFavorite.clear()
        listFavorite.addAll(movieHelper.getAllMovie())
        listFavorite.reverse()
        adapter.notifyDataSetChanged()
        if (listFavorite.isEmpty()){
            tv_null.visibility = View.VISIBLE
        }
        super.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }
}
