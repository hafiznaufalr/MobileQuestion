package net.hafiznaufalr.mobilequestion.ui.detail

import com.google.gson.Gson
import kotlinx.coroutines.*
import net.hafiznaufalr.mobilequestion.network.NetworkService
import net.hafiznaufalr.mobilequestion.util.Constant

class DetailPresenter(private val detailView: DetailView) {
    private val dataSource = NetworkService.create()

    fun getDataDetailMovie(movieId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = dataSource.getDetailMovie(movieId, Constant.API_KEY)
            try {
                val response = data.await()
                detailView.onDataResponse(response)
            } catch (throwable: Throwable) {
                detailView.onDataFailure(throwable)
            }
        }
    }

    fun getDataMovieReviews(movieId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            GlobalScope.launch(Dispatchers.Main) {
                val data = dataSource.getReviewsMovie(movieId, Constant.API_KEY)
                try {
                    val response = data.await()
                    detailView.onDataReviewsResponse(response)
                } catch (throwable: Throwable) {
                    detailView.onDataFailure(throwable)
                }
            }
        }
    }

}