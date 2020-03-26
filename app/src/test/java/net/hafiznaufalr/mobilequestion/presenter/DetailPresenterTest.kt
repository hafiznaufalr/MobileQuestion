package net.hafiznaufalr.mobilequestion.presenter

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.hafiznaufalr.mobilequestion.model.Model
import net.hafiznaufalr.mobilequestion.network.ApiService
import net.hafiznaufalr.mobilequestion.network.NetworkService
import net.hafiznaufalr.mobilequestion.ui.detail.DetailPresenter
import net.hafiznaufalr.mobilequestion.ui.detail.DetailView
import net.hafiznaufalr.mobilequestion.ui.main.MainPresenter
import net.hafiznaufalr.mobilequestion.ui.main.MainView
import net.hafiznaufalr.mobilequestion.util.Constant
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPresenterTest {
    @Mock
    lateinit var detailView: DetailView

    private lateinit var presenter: DetailPresenter
    private lateinit var dataSource: ApiService

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(detailView)
        dataSource = NetworkService.create()
    }

    @Test
    fun testDataDetailMovie(){
        var data: Deferred<Model.Movie>
        var response : Model.Movie
        val movieId = 570670
        GlobalScope.launch {
            data = dataSource.getDetailMovie(movieId,Constant.API_KEY)
            response = data.await()

            presenter.getDataDetailMovie(movieId)

            Mockito.verify(detailView).onDataResponse(response)
        }
    }

    @Test
    fun testDataReviewsMovie(){
        var data: Deferred<Model.ReviewResponse>
        var response : Model.ReviewResponse
        val movieId = 570670
        GlobalScope.launch {
            data = dataSource.getReviewsMovie(movieId,Constant.API_KEY)
            response = data.await()

            presenter.getDataMovieReviews(movieId)

            Mockito.verify(detailView).onDataReviewsResponse(response)
        }
    }

}