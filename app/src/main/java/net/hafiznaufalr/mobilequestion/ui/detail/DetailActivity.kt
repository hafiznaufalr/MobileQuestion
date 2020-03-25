package net.hafiznaufalr.mobilequestion.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import net.hafiznaufalr.mobilequestion.R
import net.hafiznaufalr.mobilequestion.db.MovieHelper
import net.hafiznaufalr.mobilequestion.model.Model.Movie
import net.hafiznaufalr.mobilequestion.util.Constant

class DetailActivity : AppCompatActivity(), DetailView {
    lateinit var presenter: DetailPresenter
    lateinit var movieHelper: MovieHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = DetailPresenter(this)
        movieHelper = MovieHelper.getInstance(this)
        movieHelper.open()
        getData()
    }



    private fun getData() {
        val movieId = intent.getIntExtra("idMovie", 0)
        presenter.getDataDetailMovie(movieId)
    }

    override fun onDataResponse(data: Movie) {
        title = data.title
        tv_title.text = data.title
        tv_release_date.text = data.releaseDate
        tv_overview.text = data.overview
        Glide.with(this).load(Constant.BASE_IMAGE_URL + data.posterPath).into(iv_poster)
        prepareFavorite(data)
        displayFavoriteStatus(movieHelper.isMovieFavorited(data.id))
    }

    private fun prepareFavorite(data: Movie) {
        ivFavorite.setOnClickListener {
            if (movieHelper.isMovieFavorited(data.id)){
                movieHelper.deleteMovie(data.id)
                Toast.makeText(this, getString(R.string.remove_favorite), Toast.LENGTH_SHORT).show()
            }else{
                movieHelper.insertMovie(data)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
            }
            displayFavoriteStatus(movieHelper.isMovieFavorited(data.id))
        }
    }

    private fun displayFavoriteStatus(favorite: Boolean) {
        if (favorite) {
            ivFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            ivFavorite.setImageResource(R.drawable.ic_not_favorite)
        }
    }

    override fun onDataFailure(throwable: Throwable) {
        Log.e("TAG", throwable.message.toString())
        Toast.makeText(this, getString(R.string.on_failed), Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

}
