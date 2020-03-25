package net.hafiznaufalr.mobilequestion.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.hafiznaufalr.mobilequestion.R
import net.hafiznaufalr.mobilequestion.model.Model.MovieResponse
import net.hafiznaufalr.mobilequestion.model.Model.Movie
import net.hafiznaufalr.mobilequestion.ui.favorite.FavoriteActivity


class MainActivity : AppCompatActivity(), MainView {
    private var listMovie: MutableList<Movie> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MainAdapter(this, listMovie)
        presenter = MainPresenter(this)
        prepareRv()
        prepareSpinner()
        getData()
        performClick()
    }

    private fun performClick() {
        btn_category.setOnClickListener {
            spinner_category.performClick()
        }
    }

    private fun prepareSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.value,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_category.adapter = adapter
        }

        spinner_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                swipe_movie.isRefreshing = true
                getData()
            }
        }
    }

    private fun prepareRv() {
        rv_movie.adapter = adapter
    }

    private fun getData() {
        when(resources.getStringArray(R.array.value)[spinner_category.selectedItemPosition]){
            getString(R.string.popular) -> {
                presenter.getDataPopularMovies()
                tv_category.text = getString(R.string.popular)
            }
            getString(R.string.upcoming) -> {
                presenter.getDataUpcomingMovies()
                tv_category.text = getString(R.string.upcoming)
            }
            getString(R.string.top_rated) ->{
                presenter.getDataTopRatedMovies()
                tv_category.text = getString(R.string.top_rated)
            }
            getString(R.string.now_playing) -> {
                presenter.getDataNowPlayingMovies()
                tv_category.text = getString(R.string.now_playing)
            }
        }
    }

    override fun onDataResponse(data: MovieResponse) {
        swipe_movie.isRefreshing = true
        listMovie.clear()
        listMovie.addAll(data.results)
        adapter.notifyDataSetChanged()
        swipe_movie.isRefreshing = false
    }

    override fun onDataFailure(throwable: Throwable) {
        swipe_movie.isRefreshing = false
        Log.e("TAG", throwable.message.toString())
        Toast.makeText(this, getString(R.string.on_failed), Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_to_favorite){
            val intent =  Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}

