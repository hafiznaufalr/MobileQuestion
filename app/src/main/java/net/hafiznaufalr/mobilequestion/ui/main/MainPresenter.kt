package net.hafiznaufalr.mobilequestion.ui.main

import kotlinx.coroutines.*
import net.hafiznaufalr.mobilequestion.network.NetworkService
import net.hafiznaufalr.mobilequestion.util.Constant

class MainPresenter(private val mainView: MainView) {
    private val dataSource = NetworkService.create()
    private var job: Job? = null

    fun getDataPopularMovies() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val data = dataSource.getPopularMovie(Constant.API_KEY)
            withContext(Dispatchers.Main) {
                try {
                    val response = data.await()
                    mainView.onDataResponse(response)
                } catch (throwable: Throwable) {
                    mainView.onDataFailure(throwable)
                }
            }
        }

    }

    fun getDataUpcomingMovies() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val data = dataSource.getUpcomingMovie(Constant.API_KEY)
            withContext(Dispatchers.Main) {
                try {
                    val response = data.await()
                    mainView.onDataResponse(response)
                } catch (throwable: Throwable) {
                    mainView.onDataFailure(throwable)
                }
            }
        }

    }

    fun getDataTopRatedMovies() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val data = dataSource.getTopRatedMovie(Constant.API_KEY)
            withContext(Dispatchers.Main) {
                try {
                    val response = data.await()
                    mainView.onDataResponse(response)
                } catch (throwable: Throwable) {
                    mainView.onDataFailure(throwable)
                }
            }
        }
    }

    fun getDataNowPlayingMovies() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val data = dataSource.getNowPlayingMovie(Constant.API_KEY)
            withContext(Dispatchers.Main) {
                try {
                    val response = data.await()
                    mainView.onDataResponse(response)
                } catch (throwable: Throwable) {
                    mainView.onDataFailure(throwable)
                }
            }
        }
    }

}