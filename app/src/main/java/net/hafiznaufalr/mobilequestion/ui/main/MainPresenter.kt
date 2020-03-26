package net.hafiznaufalr.mobilequestion.ui.main

import kotlinx.coroutines.*
import net.hafiznaufalr.mobilequestion.network.NetworkService
import net.hafiznaufalr.mobilequestion.util.Constant

class MainPresenter(private val mainView: MainView) {
    private val dataSource = NetworkService.create()

    fun getDataPopularMovies() {
        mainView.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = dataSource.getPopularMovie(Constant.API_KEY)
            try {
                val response = data.await()
                mainView.onDataResponse(response)
            } catch (throwable: Throwable) {
                mainView.onDataFailure(throwable)
            }
        }
        mainView.hideLoading()
    }

    fun getDataUpcomingMovies() {
        mainView.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = dataSource.getUpcomingMovie(Constant.API_KEY)
            try {
                val response = data.await()
                mainView.onDataResponse(response)
            } catch (throwable: Throwable) {
                mainView.onDataFailure(throwable)
            }
        }
        mainView.hideLoading()
    }

    fun getDataTopRatedMovies() {
        mainView.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = dataSource.getTopRatedMovie(Constant.API_KEY)
            try {
                val response = data.await()
                mainView.onDataResponse(response)
            } catch (throwable: Throwable) {
                mainView.onDataFailure(throwable)
            }
        }
        mainView.hideLoading()
    }

    fun getDataNowPlayingMovies() {
        mainView.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = dataSource.getNowPlayingMovie(Constant.API_KEY)
            try {
                val response = data.await()
                mainView.onDataResponse(response)
            } catch (throwable: Throwable) {
                mainView.onDataFailure(throwable)
            }
        }
        mainView.hideLoading()
    }
}