package net.hafiznaufalr.mobilequestion.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import net.hafiznaufalr.mobilequestion.R
import net.hafiznaufalr.mobilequestion.db.MovieHelper
import net.hafiznaufalr.mobilequestion.model.Model
import net.hafiznaufalr.mobilequestion.model.Model.Movie
import net.hafiznaufalr.mobilequestion.model.Model.Review
import net.hafiznaufalr.mobilequestion.util.Constant

class DetailActivity : AppCompatActivity(), DetailView {
    lateinit var presenter: DetailPresenter
    lateinit var movieHelper: MovieHelper
    lateinit var adapter: DetailAdapter
    private var listReview: MutableList<Review> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = DetailPresenter(this)
        movieHelper = MovieHelper.getInstance(this)
        movieHelper.open()
        adapter = DetailAdapter(this, listReview)
        getData()
        prepareRv()
    }

    private fun prepareRv() {
        rv_reviews.adapter = adapter
    }


    private fun getData() {
        val movieId = intent.getIntExtra("idMovie", 0)
        presenter.getDataDetailMovie(movieId)
        presenter.getDataMovieReviews(movieId)
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

    override fun onDataReviewsResponse(data: Model.ReviewResponse) {
        listReview.clear()
        listReview.addAll(data.results)
        adapter.notifyDataSetChanged()
        if (listReview.isEmpty()){
            tv_no_review.visibility = View.VISIBLE
        }
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
