package net.hafiznaufalr.mobilequestion.presenter

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.hafiznaufalr.mobilequestion.model.Model
import net.hafiznaufalr.mobilequestion.network.ApiService
import net.hafiznaufalr.mobilequestion.network.NetworkService
import net.hafiznaufalr.mobilequestion.ui.main.MainPresenter
import net.hafiznaufalr.mobilequestion.ui.main.MainView
import net.hafiznaufalr.mobilequestion.util.Constant
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainPresenterTest {
    @Mock
    lateinit var mainView: MainView

    private lateinit var presenter: MainPresenter
    private lateinit var dataSource: ApiService

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(mainView)
        dataSource = NetworkService.create()
    }

    @Test
    fun testDataMoviePopular(){
        var data: Deferred<Model.MovieResponse>
        var response : Model.MovieResponse
        GlobalScope.launch {
            data = dataSource.getPopularMovie(Constant.API_KEY)
            response = data.await()

            presenter.getDataPopularMovies()

            Mockito.verify(mainView).showLoading()
            Mockito.verify(mainView).onDataResponse(response)
            Mockito.verify(mainView).hideLoading()
        }
    }

    @Test
    fun testDataMovieUpcoming(){
        var data: Deferred<Model.MovieResponse>
        var response : Model.MovieResponse
        GlobalScope.launch {
            data = dataSource.getUpcomingMovie(Constant.API_KEY)
            response = data.await()

            presenter.getDataUpcomingMovies()

            Mockito.verify(mainView).showLoading()
            Mockito.verify(mainView).onDataResponse(response)
            Mockito.verify(mainView).hideLoading()
        }
    }

    @Test
    fun testDataMovieTopRated(){
        var data: Deferred<Model.MovieResponse>
        var response : Model.MovieResponse
        GlobalScope.launch {
            data = dataSource.getTopRatedMovie(Constant.API_KEY)
            response = data.await()

            presenter.getDataTopRatedMovies()

            Mockito.verify(mainView).showLoading()
            Mockito.verify(mainView).onDataResponse(response)
            Mockito.verify(mainView).hideLoading()
        }
    }

    @Test
    fun testDataMovieNowPlaying(){
        var data: Deferred<Model.MovieResponse>
        var response : Model.MovieResponse
        GlobalScope.launch {
            data = dataSource.getNowPlayingMovie(Constant.API_KEY)
            response = data.await()

            presenter.getDataNowPlayingMovies()

            Mockito.verify(mainView).showLoading()
            Mockito.verify(mainView).onDataResponse(response)
            Mockito.verify(mainView).hideLoading()
        }
    }




}