package net.hafiznaufalr.mobilequestion.ui.detail

import kotlinx.coroutines.*
import net.hafiznaufalr.mobilequestion.network.NetworkService
import net.hafiznaufalr.mobilequestion.util.Constant

class  DetailPresenter(private val detailView: DetailView) {
    private val dataSource = NetworkService.create()
    private var job: Job? = null

    fun getDataDetailMovie(movieId: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val data = dataSource.getDetailMovie(movieId, Constant.API_KEY)
            withContext(Dispatchers.Main) {
                try {
                    val response = data.await()
                    detailView.onDataResponse(response)
                } catch (throwable: Throwable) {
                    detailView.onDataFailure(throwable)
                }
            }
        }

    }
}