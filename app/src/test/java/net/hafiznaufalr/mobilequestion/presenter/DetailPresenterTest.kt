package net.hafiznaufalr.mobilequestion.presenter

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.hafiznaufalr.mobilequestion.model.Model
import net.hafiznaufalr.mobilequestion.network.ApiService
import net.hafiznaufalr.mobilequestion.network.NetworkService
import net.hafiznaufalr.mobilequestion.ui.detail.DetailPresenter
import net.hafiznaufalr.mobilequestion.ui.detail.DetailView
import net.hafiznaufalr.mobilequestion.util.Constant
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import retrofit2.Response

class DetailPresenterTest {
    @Mock
    lateinit var detailView: DetailView

    @Mock
    private lateinit var dataSource : ApiService

    private lateinit var presenter: DetailPresenter


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(detailView)
        dataSource = NetworkService.create()
    }

    @Test
    fun testDataMovieDetail(){
        var data: Deferred<Model.Movie>
        var response : Model.Movie
        val movieId = 570670
        GlobalScope.launch {
            data = dataSource.getDetailMovie(movieId,Constant.API_KEY)
            response = data.await()

            presenter.getDataDetailMovie(movieId)

            verify(detailView).onDataResponse(response)
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

            verify(detailView).onDataReviewsResponse(response)
        }
    }

}