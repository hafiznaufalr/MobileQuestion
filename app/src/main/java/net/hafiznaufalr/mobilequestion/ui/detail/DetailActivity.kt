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
import net.hafiznaufalr.mobilequestion.model.Model
import net.hafiznaufalr.mobilequestion.util.Constant

class DetailActivity : AppCompatActivity(), DetailView {
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = DetailPresenter(this)
        getData()
    }

    private fun getData() {
        val movieId = intent.getIntExtra("idMovie", 0)
        presenter.getDataDetailMovie(movieId)
    }

    override fun onDataResponse(data: Model.Detail) {
        title = data.title
        tv_title.text = data.title
        tv_release_date.text = data.releaseDate
        tv_overview.text = data.overview
        Glide.with(this).load(Constant.BASE_IMAGE_URL + data.posterPath).into(iv_poster)
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
